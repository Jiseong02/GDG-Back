package com.gdg.gdgback.DTO.Response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@Data
public class DiaryReadResponseDto {
    String id;
    String userId;
    String counselId;

    @CreatedDate
    LocalDateTime date;
    byte[] picture;
    String[] category;
    int score;
    String title;
    String content;
}
