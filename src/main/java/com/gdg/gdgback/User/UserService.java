package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;

public interface UserService {
    void createUser(UserCreateRequestDto userCreateRequestDto);
    UserReadResponseDto readUser(String id);
    UserReadListResponseDto readUserList();
    void deleteUser(UserDeleteRequestDto deleteRequestDto);
    void validateUserExists(String id);
    void validateUserNotExists(String id);
}
