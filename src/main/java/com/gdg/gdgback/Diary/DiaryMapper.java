package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;

public class DiaryMapper {
    public static DiaryDocument dtoToDocument(DiaryCreateRequestDto createRequestDto) {
        return DiaryDocument.builder()
                .userId(createRequestDto.getUserId())
                .counselId(createRequestDto.getCounselId())
                .picture(createRequestDto.getPicture())
                .category(createRequestDto.getCategory())
                .score(createRequestDto.getScore())
                .title(createRequestDto.getTitle())
                .content(createRequestDto.getContent())
                .build();
    }

    public static DiaryReadResponseDto documentToReadResponseDto(DiaryDocument diaryDocument, CounselReadResponseDto counsel) {
        return DiaryReadResponseDto.builder()
                .id(diaryDocument.getId())
                .userId(diaryDocument.getUserId())
                .counsel(counsel)
                .date(diaryDocument.getDate())
                .picture(diaryDocument.getPicture())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }
}
