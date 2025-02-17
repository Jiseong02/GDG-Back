package com.gdg.gdgback.DTO.Response.User;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class UserReadListResponseDto {
    ArrayList<UserReadResponseDto> users;
}
