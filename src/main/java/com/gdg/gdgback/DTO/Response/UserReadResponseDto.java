package com.gdg.gdgback.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserReadResponseDto {
    private String id;
    private String name;
}
