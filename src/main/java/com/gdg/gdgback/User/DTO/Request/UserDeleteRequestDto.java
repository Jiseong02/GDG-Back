package com.gdg.gdgback.User.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDeleteRequestDto {
    @NotNull(message = "id는 필수 입력 값입니다.")
    private String id;
}
