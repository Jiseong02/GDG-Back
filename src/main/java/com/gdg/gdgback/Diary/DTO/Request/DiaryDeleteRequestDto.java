package com.gdg.gdgback.Diary.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiaryDeleteRequestDto {
    @NotNull(message = "id는 필수 입력 값입니다.")
    private String id;
}
