package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Diary.DTO.Request.*;
import com.gdg.gdgback.Diary.DTO.Response.*;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    private final CounselService counselService;

    private final Validator validator;

    @Autowired
    DiaryServiceImpl(CounselService counselService, DiaryRepository diaryRepository, Validator validator) {
        this.diaryRepository = diaryRepository;

        this.counselService = counselService;

        this.validator = validator;
    }

    @Override
    public String createDiary(DiaryCreateRequestDto createRequestDto) throws UserNotExistsException {
        validator.validateUserExists(createRequestDto.getUserId());

        return diaryRepository.save(DiaryMapper.map(createRequestDto)).getId();
    }

    @Override
    public DiaryReadResponseDto readDiary(String id) throws DiaryNotFoundException {
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
    public DiaryReadListResponseDto readDiaryListByUserId(String id) throws UserNotExistsException {
        validator.validateUserExists(id);

        List<DiaryDocument> diaryDocumentList = diaryRepository.findAllByUserId(id);
        return convertDocumentListToListDto(diaryDocumentList);
    }

    @Override
    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new DiaryNotFoundException(deleteRequestDto.getId()));
        diaryRepository.delete(diaryDocument);
    }

    private DiaryReadResponseDto convertDocumentToDto(DiaryDocument document) {
        try {
            CounselReadResponseDto counsel = counselService.readCounsel(document.getCounselId());
            return DiaryMapper.map(document, counsel);
        } catch (Exception e) {
            return DiaryMapper.map(document, null);
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
