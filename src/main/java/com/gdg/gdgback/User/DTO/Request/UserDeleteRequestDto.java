package com.gdg.gdgback.User.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDeleteRequestDto {
    private String id;
}
