package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.Diary.DiaryCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Diary.DiaryDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.Exception.DiaryNotFoundException;
import com.gdg.gdgback.Exception.UserNotFoundException;

public interface DiaryService {
    String createDiary(DiaryCreateRequestDto createRequestDto) throws UserNotFoundException;
    DiaryReadResponseDto readDiary(String id) throws DiaryNotFoundException;
    void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException;
}
