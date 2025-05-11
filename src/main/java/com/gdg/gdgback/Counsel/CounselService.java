package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.*;
import com.gdg.gdgback.Counsel.DTO.Response.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public interface CounselService {
    CounselReadResponseDto readCounsel(String id);
    CounselReadListResponseDto readCounselList();
    CounselReadListResponseDto readCounselByUserId(String id);
    CounselReadListResponseDto readCounselByUserIdAndYearMonth(String id, YearMonth yearMonth);
    CounselCreateResponseDto createCounsel(HttpSession session, CounselCreateRequestDto createRequestDto);
    void deleteCounselsOverTimeLimit();
    void endCounsel(CounselEndRequestDto counselEndRequestDto);
    void deleteCounsel(CounselDeleteRequestDto deleteRequestDto);
    void validateCounselExists(String id);
}
