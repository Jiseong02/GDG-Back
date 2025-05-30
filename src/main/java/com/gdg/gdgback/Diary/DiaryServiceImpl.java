package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Diary.DTO.Request.*;
import com.gdg.gdgback.Diary.DTO.Response.*;
import com.gdg.gdgback.Global.Validator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    private final CounselService counselService;

    private final Validator validator;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    DiaryServiceImpl(CounselService counselService, DiaryRepository diaryRepository, Validator validator) {
        this.diaryRepository = diaryRepository;

        this.counselService = counselService;

        this.validator = validator;
    }

    @Override
    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        validator.validateUserExists(createRequestDto.getUserId());
        return diaryRepository.save(DiaryMapper.map(createRequestDto)).getId();
    }

    @Override
    public DiaryReadResponseDto readDiary(String id) {
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(() -> new DiaryNotFoundException(id));
        return convertDocumentToDto(diaryDocument);
    }

    @Override
    public DiaryReadListResponseDto readDiaryList() {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAll();
        return convertDocumentListToListDto(diaryDocumentList);
    }

    @Override
    public DiaryReadListResponseDto readDiaryListByUserId(String id) {
        validator.validateUserExists(id);

        List<DiaryDocument> diaryDocumentList = diaryRepository.findAllByUserIdOrderByDateDesc(id);
        return convertDocumentListToListDto(diaryDocumentList);
    }

    @Override
    public DiaryReadListResponseDto readDiaryListByUserIdAndYearMonth(String id, YearMonth yearMonth) {
        validator.validateUserExists(id);

        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        Query query = new Query()
                .addCriteria(Criteria.where("userId").is(id))
                .addCriteria(Criteria.where("date").gte(start).lte(end));

        return convertDocumentListToListDto(mongoTemplate.find(query, DiaryDocument.class));
    }

    @Override
    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new DiaryNotFoundException(deleteRequestDto.getId()));
        diaryRepository.delete(diaryDocument);
    }

    @Async
    @Override
    public CompletableFuture<String> uploadDiaryImage(DiaryImageUploadRequestDto imageUploadRequestDto) {
        DiaryDocument diaryDocument = diaryRepository.findById(imageUploadRequestDto.getId())
                .orElseThrow(() -> new DiaryNotFoundException(imageUploadRequestDto.getId()));

        String imageUrl = saveImage(imageUploadRequestDto.getImage());

        diaryDocument.setImageUrl(imageUrl);
        diaryRepository.save(diaryDocument);
        return CompletableFuture.completedFuture(imageUrl);
    }

    private String saveImage(MultipartFile image) {
        try {
            String UPLOAD_PATH = "/app/images";
            String fileName = Paths.get(UUID.randomUUID() + "_" + image.getOriginalFilename()).getFileName().toString();
            Path path = Paths.get(UPLOAD_PATH, fileName);
            Files.createDirectories(path.getParent());
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + fileName;
        } catch (IOException e) {
            throw new DiaryImageIOException("Failed to save image file.");
        }
    }

    private DiaryReadResponseDto convertDocumentToDto(DiaryDocument document) {
        try {
            String counselId = document.getCounselId();
            if (counselId == null) {
                return DiaryMapper.map(document, null);
            } else {
                return DiaryMapper.map(document, counselService.readCounsel(counselId));
            }
        } catch (CounselNotExistsException e) {
            return DiaryMapper.map(document, null); // 얘가 범인인가?
        }
    }

    private DiaryReadListResponseDto convertDocumentListToListDto(List<DiaryDocument> documentList) {
        ArrayList<DiaryReadResponseDto> dtoArrayList = documentList.stream()
                .map(this::convertDocumentToDto)
                .collect(Collectors.toCollection(ArrayList::new));

        return DiaryReadListResponseDto.builder()
                .diaries(dtoArrayList)
                .build();
    }
}
