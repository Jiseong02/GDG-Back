package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;

import java.util.Date;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class DiaryMapper {
    public static DiaryDocument map(DiaryCreateRequestDto createRequestDto) {
        Date date = Date.from(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant());
        return DiaryDocument.builder()
                .userId(createRequestDto.getUserId())
                .counselId(createRequestDto.getCounselId())
                .picture(createRequestDto.getPicture())
                .expected(createRequestDto.getExpected())
                .category(createRequestDto.getCategory())
                .score(createRequestDto.getScore())
                .date(date)
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
                .picture(diaryDocument.getPicture())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }
}
