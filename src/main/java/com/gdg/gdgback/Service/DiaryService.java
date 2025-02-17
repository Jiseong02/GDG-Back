package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.Diary.DiaryCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Diary.DiaryReadRequestDto;
import com.gdg.gdgback.DTO.Response.DiaryReadResponseDto;

public interface DiaryService {
    String createDiary(DiaryCreateRequestDto createRequestDto);
    DiaryReadResponseDto readDiary(DiaryReadRequestDto readRequestDto);
}
