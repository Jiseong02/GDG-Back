package com.gdg.gdgback.Diary.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiaryCreateRequestDto {
    @NotNull(message = "userId는 필수 입력 값입니다.")
    private String userId;
    private String counselId;

    private byte[] picture;
    private String[] category;
    private int score;
    @NotNull(message = "title은 필수 입력 값입니다.")
    private String title;
    @NotNull(message = "content는 필수 입력 값입니다.")
    private String content;
}
