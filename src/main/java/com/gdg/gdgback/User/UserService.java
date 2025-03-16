package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;

public interface UserService {
    void createUser(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException;
    UserReadResponseDto readUser(String id) throws UserNotExistsException;
    UserReadListResponseDto readUserList();
    void deleteUser(UserDeleteRequestDto deleteRequestDto) throws UserNotExistsException;
    void validateUserExists(String id) throws UserNotExistsException;
    void validateUserNotExists(String id) throws UserAlreadyExistsException;
}
