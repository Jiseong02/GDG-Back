package com.gdg.gdgback.Diary.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class DiaryReadListResponseDto {
    ArrayList<DiaryReadResponseDto> diaries;
}
