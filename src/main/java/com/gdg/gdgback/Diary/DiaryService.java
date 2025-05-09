package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryImageUploadRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DiaryService {
    String createDiary(DiaryCreateRequestDto createRequestDto) throws UserNotExistsException;
    DiaryReadResponseDto readDiary(String id) throws DiaryNotFoundException;
    DiaryReadListResponseDto readDiaryList();
    DiaryReadListResponseDto readDiaryListByUserId(String id) throws UserNotExistsException;
    void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException;

    String uploadDiaryImage(DiaryImageUploadRequestDto imageUploadRequestDto);
}
