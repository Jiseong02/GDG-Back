package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.*;
import com.gdg.gdgback.Counsel.DTO.Response.*;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CounselService {
    CounselReadResponseDto readCounsel(String id) throws CounselNotExistsException;
    CounselReadListResponseDto readCounselList();
    CounselReadListResponseDto readCounselByUserId(String id) throws UserNotExistsException;
    CounselCreateResponseDto createCounsel(CounselCreateRequestDto createRequestDto) throws UserNotExistsException, IOException;
    void deleteCounselsOverTimeLimit();
    void endCounsel(CounselEndRequestDto counselEndRequestDto) throws CounselNotExistsException;
    void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws CounselNotExistsException;
    void validateCounselExists(String id) throws CounselNotExistsException;
}
