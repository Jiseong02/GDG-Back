package com.gdg.gdgback.Diary.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class DiaryCreateRequestDto {
    @NotNull(message = "userId는 필수 입력 값입니다.")
    private String userId;
    private String counselId;

    private MultipartFile picture;
    private String[] category;
    private int score;
    private Boolean expected;
    @NotNull(message = "title 은 필수 입력 값입니다.")
    private String title;
    @NotNull(message = "content 는 필수 입력 값입니다.")
    private String content;
}
