package com.gdg.gdgback.DTO.Response.User;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserReadResponseDto {
    private String id;
    private String name;
    private LocalDateTime date;
}
