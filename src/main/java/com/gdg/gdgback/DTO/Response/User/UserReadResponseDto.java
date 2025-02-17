package com.gdg.gdgback.DTO.Response.User;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UserReadResponseDto {
    private String id;
    private String name;
    private Date date;
}
