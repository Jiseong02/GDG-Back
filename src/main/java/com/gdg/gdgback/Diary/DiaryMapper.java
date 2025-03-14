package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselDocument;
import com.gdg.gdgback.Counsel.CounselMapper;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;

import java.util.ArrayList;
import java.util.List;

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

    public static DiaryReadResponseDto documentToReadResponseDto(DiaryDocument diaryDocument) {
        return DiaryReadResponseDto.builder()
                .id(diaryDocument.getId())
                .userId(diaryDocument.getUserId())
                .date(diaryDocument.getDate())
                .picture(diaryDocument.getPicture())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }

    public static DiaryReadListResponseDto documentToReadResponseDto(List<DiaryDocument> diaryDocumentList) {
        ArrayList<DiaryReadResponseDto> diaryList = new ArrayList<>();
        for(DiaryDocument diaryDocument: diaryDocumentList) {
            diaryList.add(documentToReadResponseDto(diaryDocument));
        }

        return DiaryReadListResponseDto.builder()
                .diaries(diaryList)
                .build();
    }
}
