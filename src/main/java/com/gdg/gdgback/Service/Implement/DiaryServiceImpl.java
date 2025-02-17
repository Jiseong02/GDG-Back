package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.Diary.DiaryCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Diary.DiaryReadRequestDto;
import com.gdg.gdgback.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.Document.DiaryDocument;
import com.gdg.gdgback.Repository.DiaryRepository;
import com.gdg.gdgback.Service.DiaryService;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override
    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        return diaryRepository.save(
                DiaryDocument.builder()
                        .userId(createRequestDto.getUserId())
                        .counselId(createRequestDto.getCounselId())
                        .picture(createRequestDto.getPicture())
                        .category(createRequestDto.getCategory())
                        .score(createRequestDto.getScore())
                        .title(createRequestDto.getTitle())
                        .content(createRequestDto.getContent())
                        .build()
        ).getId();
    }

    @Override
    public DiaryReadResponseDto readDiary(DiaryReadRequestDto readRequestDto) {
        DiaryDocument diaryDocument = diaryRepository.findById(readRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일지입니다."));

        return DiaryReadResponseDto.builder()
                .id(diaryDocument.getId())
                .userId(diaryDocument.getUserId())
                .counselId(diaryDocument.getCounselId())
                .date(diaryDocument.getDate())
                .picture(diaryDocument.getPicture())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }
}
