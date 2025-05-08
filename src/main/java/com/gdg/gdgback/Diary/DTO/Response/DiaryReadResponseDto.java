package com.gdg.gdgback.Diary.DTO.Response;

import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DiaryReadResponseDto {
    String id;
    String userId;

    CounselReadResponseDto counsel;

    LocalDateTime date;
    String imageUrl;
    Boolean expected;
    String[] category;
    int score;
    String title;
    String content;
}
