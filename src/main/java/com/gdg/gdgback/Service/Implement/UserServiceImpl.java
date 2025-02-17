package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserReadRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;
import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Mapper.UserMapper;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        if(userRepository.findById(userCreateRequestDto.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        userRepository.save(UserMapper.toUserDocument(userCreateRequestDto));
    }

    public UserReadResponseDto readUser(UserReadRequestDto readRequestDto) {
        UserDocument userDocument = userRepository.findById(readRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserMapper.toUserReadResponseDto(userDocument);
    }

    public UserReadListResponseDto readUserList() {
        List<UserDocument> userDocumentList = userRepository.findAll();
        return UserMapper.toUserReadListResponseDto(userDocumentList);
    }
}
