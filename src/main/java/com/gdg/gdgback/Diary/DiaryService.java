package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.*;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.User.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService{
    private final UserService userService;
    private final CounselRepository counselRepository;
    private final DiaryRepository diaryRepository;

    DiaryService(UserService userService, CounselRepository counselRepository, DiaryRepository diaryRepository) {
        this.userService = userService;
        this.counselRepository = counselRepository;
        this.diaryRepository = diaryRepository;
    }

    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        return diaryRepository.save(DiaryMapper.dtoToDocument(createRequestDto)).getId();
    }

    public DiaryReadResponseDto readDiary(String id) {
        // 일지 조회
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);
        DiaryReadResponseDto responseDto = DiaryMapper.documentToReadResponseDto(diaryDocument);

        // 상담 ID를 통해 조회한 상담 대입
        responseDto.setCounsel(
                counselRepository.findById(diaryDocument.getCounselId())
                        .map(CounselMapper::documentToDto)
                        .orElse(null)
        );

        return responseDto;
    }

    public DiaryReadListResponseDto readDiaryList() {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAll();

        ArrayList<DiaryReadResponseDto> diaryReadResponseDtoList = documentListToReadResponseDtoList(diaryDocumentList);
        return DiaryReadListResponseDto.builder()
                .diaries(diaryReadResponseDtoList)
                .build();
    }

    public DiaryReadListResponseDto readDiaryListByUserId(String id) {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAllByUserId(id);

        ArrayList<DiaryReadResponseDto> diaryReadResponseDtoList = documentListToReadResponseDtoList(diaryDocumentList);
        return DiaryReadListResponseDto.builder()
                .diaries(diaryReadResponseDtoList)
                .build();
    }

    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(DiaryNotFoundException::new);

        diaryRepository.delete(diaryDocument);
    }

    private DiaryReadResponseDto
}
