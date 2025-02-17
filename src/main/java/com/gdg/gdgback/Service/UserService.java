package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;

public interface UserService {
     void createUser(UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException;
     UserReadResponseDto readUser(String id) throws IllegalArgumentException;
     UserReadListResponseDto readUserList();
}
