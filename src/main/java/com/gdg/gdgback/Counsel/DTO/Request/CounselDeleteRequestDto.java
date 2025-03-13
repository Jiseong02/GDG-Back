package com.gdg.gdgback.Counsel.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CounselDeleteRequestDto {
    @NotNull(message="id는 필수 값입니다.")
    private String id;
}
