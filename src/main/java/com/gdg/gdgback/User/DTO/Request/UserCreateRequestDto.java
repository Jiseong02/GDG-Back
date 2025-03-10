package com.gdg.gdgback.User.DTO.Request;

import lombok.*;

@Builder
@Data
public class UserCreateRequestDto {
    private String id;
    private String name;
}
