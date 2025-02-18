package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Counsel.CounselDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;
import com.gdg.gdgback.Exception.CounselNotFoundException;
import com.gdg.gdgback.Exception.UserNotFoundException;

public interface CounselService {
    String createCounsel(CounselCreateRequestDto createRequestDto) throws UserNotFoundException;
    CounselReadResponseDto readCounsel(String id) throws CounselNotFoundException;
    void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws CounselNotFoundException;
}
