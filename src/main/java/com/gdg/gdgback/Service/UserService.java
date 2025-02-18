package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;
import com.gdg.gdgback.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.Exception.UserNotFoundException;

public interface UserService {
     void createUser(UserCreateRequestDto createRequestDto) throws UserAlreadyExistsException;
     UserReadResponseDto readUser(String id) throws UserNotFoundException;
     UserReadListResponseDto readUserList();
     void deleteUser(UserDeleteRequestDto deleteRequestDto) throws UserNotFoundException;
}
