package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.UserReadResponseDto;

public interface UserService {
     void addUser(UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException;
     UserReadResponseDto getUserById(String id) throws IllegalArgumentException;
     UserReadListResponseDto getUserList();
}
