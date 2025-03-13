package com.gdg.gdgback.Counsel.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CounselCreateRequestDto {
    @NotNull(message="userId는 필수 값입니다.")
    private String userId;
}
