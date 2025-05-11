package com.gdg.gdgback.Analysis;

import com.gdg.gdgback.Analysis.DTO.AnalysisResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@Profile("!test")
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    AnalysisService analysisService;

    AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping
    ResponseEntity<AnalysisResponseDto> getAnalysisByUserIdAndYearMonth(@RequestParam("id") String id, @RequestParam("yearMonth") String yearMonth) {
        return ResponseEntity.ok().body(analysisService.analyzeByUserIdAndYearMonth(id, YearMonth.parse(yearMonth)));
    }
}
