package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.*;
import com.gdg.gdgback.Counsel.DTO.Response.*;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CounselService {
    CounselReadResponseDto readCounsel(String id);
    CounselReadListResponseDto readCounselList();
    CounselReadListResponseDto readCounselByUserId(String id);
    CounselCreateResponseDto createCounsel(CounselCreateRequestDto createRequestDto);
    void deleteCounselsOverTimeLimit();
    void endCounsel(CounselEndRequestDto counselEndRequestDto);
    void deleteCounsel(CounselDeleteRequestDto deleteRequestDto);
    void validateCounselExists(String id);
}
