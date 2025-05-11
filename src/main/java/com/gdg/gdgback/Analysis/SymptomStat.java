package com.gdg.gdgback.Analysis;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SymptomStat {
    private String name;
    private int count;
}
