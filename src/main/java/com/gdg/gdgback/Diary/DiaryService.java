package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.*;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService{
    private final UserRepository userRepository;
    private final CounselRepository counselRepository;
    private final DiaryRepository diaryRepository;

    @Autowired
    DiaryService(UserRepository userRepository, CounselRepository counselRepository, DiaryRepository diaryRepository) {
        this.userRepository = userRepository;
        this.counselRepository = counselRepository;
        this.diaryRepository = diaryRepository;
    }

    public String createDiary(DiaryCreateRequestDto createRequestDto) throws UserNotExistsException {
        if(!userRepository.existsById(createRequestDto.getUserId())) {
            throw new UserNotExistsException();
        }
        return diaryRepository.save(DiaryMapper.map(createRequestDto)).getId();
    }

    public DiaryReadResponseDto readDiary(String id) throws DiaryNotFoundException{
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);
        return convertDocumentToDto(diaryDocument);
    }

    public DiaryReadListResponseDto readDiaryList() {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAll();
        return convertDocumentListToListDto(diaryDocumentList);
    }

    public DiaryReadListResponseDto readDiaryListByUserId(String id) throws UserNotExistsException {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException();
        }

        List<DiaryDocument> diaryDocumentList = diaryRepository.findAllByUserId(id);
        return convertDocumentListToListDto(diaryDocumentList);
    }

    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(DiaryNotFoundException::new);
        diaryRepository.delete(diaryDocument);
    }

    private DiaryReadResponseDto convertDocumentToDto(DiaryDocument document) {
        if(document.getCounselId() == null) {
            return DiaryMapper.map(document, null);
        }
        CounselReadResponseDto counsel = counselRepository.findById(document.getCounselId())
                .map(CounselMapper::map)
                .orElse(null);
        return DiaryMapper.map(document, counsel);
    }

    private DiaryReadListResponseDto convertDocumentListToListDto(List<DiaryDocument> documentList) {
        ArrayList<DiaryReadResponseDto> dtoArrayList = new ArrayList<>();
        for(DiaryDocument document : documentList) {
            DiaryReadResponseDto dto = convertDocumentToDto(document);
            dtoArrayList.add(dto);
        }
        return DiaryReadListResponseDto.builder().diaries(dtoArrayList).build();
    }
}
