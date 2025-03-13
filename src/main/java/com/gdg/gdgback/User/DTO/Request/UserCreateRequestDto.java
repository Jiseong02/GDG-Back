package com.gdg.gdgback.User.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
public class UserCreateRequestDto {
    @NotNull(message = "id는 필수 입력 값입니다.")
    private String id;
    private String name;
}
