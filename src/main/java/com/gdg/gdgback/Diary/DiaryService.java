package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryImageUploadRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.concurrent.CompletableFuture;

@Service
public interface DiaryService {
    String createDiary(DiaryCreateRequestDto createRequestDto) throws UserNotExistsException;
    DiaryReadResponseDto readDiary(String id) throws DiaryNotFoundException;
    DiaryReadListResponseDto readDiaryList();
    DiaryReadListResponseDto readDiaryListByUserId(String id) throws UserNotExistsException;
    DiaryReadListResponseDto readDiaryListByUserIdAndYearMonth(String id, YearMonth yearMonth);
    void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException;

    CompletableFuture<String> uploadDiaryImage(DiaryImageUploadRequestDto imageUploadRequestDto);
}
