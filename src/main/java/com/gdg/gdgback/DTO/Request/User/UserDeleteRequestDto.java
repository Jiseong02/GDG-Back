package com.gdg.gdgback.DTO.Request.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDeleteRequestDto {
    private String id;
}
