package com.gdg.gdgback.Diary.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiaryDeleteRequestDto {
    private String id;
}
