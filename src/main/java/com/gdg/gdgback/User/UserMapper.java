package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserReadResponseDto map(UserDocument userDocument) {
        return UserReadResponseDto.builder()
                .id(userDocument.getId())
                .name(userDocument.getName())
                .date(userDocument.getDate())
                .build();
    }
    public static UserReadListResponseDto map(List<UserDocument> userDocumentList) {
        ArrayList<UserReadResponseDto> users = userDocumentList.stream().map(UserMapper::map).collect(Collectors.toCollection(ArrayList::new));
        return UserReadListResponseDto.builder()
                .users(users)
                .build();
    }

    public static UserDocument map(UserCreateRequestDto userCreateRequestDto) {
        return UserDocument.builder()
                .id(userCreateRequestDto.getId())
                .name(userCreateRequestDto.getName())
                .date(LocalDateTime.now())
                .build();
    }
}
