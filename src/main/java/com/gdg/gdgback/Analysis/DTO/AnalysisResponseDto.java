package com.gdg.gdgback.Analysis.DTO;

import com.gdg.gdgback.Analysis.ExpectationStat;
import com.gdg.gdgback.Analysis.ScoreStat;
import com.gdg.gdgback.Analysis.SymptomStat;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class AnalysisResponseDto {
    ArrayList<SymptomStat> symptomStats;
    ExpectationStat expectationStat;
    ArrayList<ScoreStat> scoreStats;
}
