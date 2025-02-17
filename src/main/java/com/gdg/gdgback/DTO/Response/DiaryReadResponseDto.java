package com.gdg.gdgback.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class DiaryReadResponseDto {
    String id;
    String userId;
    String counselId;

    Date date;
    byte[] picture;
    String[] category;
    int score;
    String title;
    String content;
}
