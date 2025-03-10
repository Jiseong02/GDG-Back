package com.gdg.gdgback.Diary.DTO.Request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Data
public class DiaryCreateRequestDto {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String counselId;

    private byte[] picture;
    private String[] category;
    private int score;
    private String title;
    private String content;
}
