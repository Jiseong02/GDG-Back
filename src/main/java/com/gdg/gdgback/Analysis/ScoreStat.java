package com.gdg.gdgback.Analysis;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ScoreStat {
    private LocalDateTime date;
    private int score;
}
