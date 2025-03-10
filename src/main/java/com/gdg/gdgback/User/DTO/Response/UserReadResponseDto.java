package com.gdg.gdgback.User.DTO.Response;

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
