package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Counsel.CounselDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;

public interface CounselService {
    String createCounsel(CounselCreateRequestDto createRequestDto) throws IllegalArgumentException;
    CounselReadResponseDto readCounsel(String id) throws IllegalArgumentException;
    void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws IllegalArgumentException;
}
