package com.gdg.gdgback.Analysis;

import com.gdg.gdgback.Analysis.DTO.AnalysisResponseDto;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public interface AnalysisService {
    AnalysisResponseDto analyzeByUserIdAndYearMonth(String id, YearMonth yearMonth);
}
