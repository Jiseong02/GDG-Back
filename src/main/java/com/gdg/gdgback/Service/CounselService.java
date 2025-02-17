package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Counsel.CounselReadRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;

public interface CounselService {
    String createCounsel(CounselCreateRequestDto createRequestDto);
    CounselReadResponseDto readCounsel(CounselReadRequestDto readRequestDto);
}
