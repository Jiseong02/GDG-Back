package com.gdg.gdgback.DTO.Request.User;

import lombok.*;

@Builder
@Data
public class UserCreateRequestDto {
    private String id;
    private String name;
}
