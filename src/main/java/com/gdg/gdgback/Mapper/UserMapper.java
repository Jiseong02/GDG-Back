package com.gdg.gdgback.Mapper;

import com.gdg.gdgback.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.UserReadResponseDto;
import com.gdg.gdgback.Document.UserDocument;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserReadResponseDto toUserReadResponseDto(UserDocument userDocument) {
        return UserReadResponseDto.builder()
                .id(userDocument.getId())
                .name(userDocument.getName())
                .build();
    }
    public static UserReadListResponseDto toUserReadListResponseDto(List<UserDocument> userDocumentList) {
        ArrayList<UserReadResponseDto> userList = new ArrayList<>();
        for(UserDocument userDocument: userDocumentList) {
            userList.add(UserMapper.toUserReadResponseDto(userDocument));
        }
        return UserReadListResponseDto.builder()
                .users(userList)
                .build();
    }

    public static UserDocument toUserDocument(UserCreateRequestDto userCreateRequestDto) {
        return UserDocument.builder()
                .id(userCreateRequestDto.getId())
                .name(userCreateRequestDto.getName())
                .build();
    }
}
