package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserReadRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;

public interface UserService {
     void createUser(UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException;
     UserReadResponseDto readUser(UserReadRequestDto readRequestDto) throws IllegalArgumentException;
     UserReadListResponseDto readUserList();
}
