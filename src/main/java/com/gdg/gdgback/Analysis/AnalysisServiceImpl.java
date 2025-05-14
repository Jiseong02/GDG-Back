package com.gdg.gdgback.Analysis;

import com.gdg.gdgback.Analysis.DTO.AnalysisResponseDto;
import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.Diary.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    CounselService counselService;
    DiaryService diaryService;

    @Autowired
    AnalysisServiceImpl(CounselService counselService, DiaryService diaryService) {
        this.counselService = counselService;
        this.diaryService = diaryService;
    }

    @Override
    public AnalysisResponseDto analyzeByUserIdAndYearMonth(String userId, YearMonth yearMonth) {
        List<DiaryReadResponseDto> diaries = diaryService.readDiaryListByUserIdAndYearMonth(userId, yearMonth).getDiaries();

        return AnalysisResponseDto.builder()
                .diaryNum(diaries.size())
                .symptomStats(getSymptomStats(diaries))
                .expectationStat(getExpectationStat(diaries))
                .scoreStats(getScoreStats(diaries))
                .build();
    }

    private ArrayList<SymptomStat> getSymptomStats(List<DiaryReadResponseDto> diaries) {
        return countCategoryFrequency(diaries).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> new SymptomStat(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Map<String, Integer> countCategoryFrequency(List<DiaryReadResponseDto> diaries) {
        return diaries.stream()
                .filter(diary -> diary.getCategory() != null)
                .flatMap(diary -> Arrays.stream(diary.getCategory()))
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> 1,
                        Integer::sum
                ));
    }

    private ExpectationStat getExpectationStat(List<DiaryReadResponseDto> diaries) {
        int predicted = 0, nonPredicted = 0;

        for (DiaryReadResponseDto diary : diaries) {
            if(diary.getExpected() == null) continue;

            if(diary.getExpected()) predicted++;
            else nonPredicted++;
        }
        return ExpectationStat.builder().O(predicted).X(nonPredicted).build();
    }

    private ArrayList<ScoreStat> getScoreStats(List<DiaryReadResponseDto> diaries) {
        return diaries.stream()
                .filter(diary -> diary.getScore() != null)
                .map(diary -> new ScoreStat(diary.getDate(), diary.getScore()))
                .sorted(Comparator.comparing(ScoreStat::getDate).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
