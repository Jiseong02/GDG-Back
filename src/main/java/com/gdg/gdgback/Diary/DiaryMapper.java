package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;

import java.time.LocalDateTime;
public class DiaryMapper {
    public static DiaryDocument map(DiaryCreateRequestDto createRequestDto) {
        return DiaryDocument.builder()
                .userId(createRequestDto.getUserId())
                .counselId(createRequestDto.getCounselId())
                .expected(createRequestDto.getExpected())
                .category(createRequestDto.getCategory())
                .score(createRequestDto.getScore())
                .date(LocalDateTime.now())
                .title(createRequestDto.getTitle())
                .content(createRequestDto.getContent())
                .build();
    }

    public static DiaryReadResponseDto map(DiaryDocument diaryDocument, CounselReadResponseDto counsel) {
        return DiaryReadResponseDto.builder()
                .id(diaryDocument.getId())
                .userId(diaryDocument.getUserId())
                .counsel(counsel)
                .expected(diaryDocument.getExpected())
                .date(diaryDocument.getDate())
                .imageUrl(diaryDocument.getImageUrl())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }
}
