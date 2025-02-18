package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;
import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        if(userRepository.findById(userCreateRequestDto.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        UserDocument userDocument = UserDocument.builder()
                .id(userCreateRequestDto.getId())
                .name(userCreateRequestDto.getName())
                .build();

        userRepository.save(userDocument);
    }

    public UserReadResponseDto readUser(String id) {
        UserDocument userDocument = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return UserReadResponseDto.builder()
                .id(userDocument.getId())
                .name(userDocument.getName())
                .date(userDocument.getDate())
                .build();
    }

    public UserReadListResponseDto readUserList() {
        List<UserDocument> userDocumentList = userRepository.findAll();
        ArrayList<UserReadResponseDto> users = new ArrayList<>();
        for(UserDocument userDocument : userDocumentList) {
            users.add(
                    UserReadResponseDto.builder()
                            .id(userDocument.getId())
                            .name(userDocument.getName())
                            .date(userDocument.getDate())
                            .build()
            );
        }
        return UserReadListResponseDto.builder().users(users).build();
    }

    @Override
    public void deleteUser(UserDeleteRequestDto deleteRequestDto) throws IllegalArgumentException {
        UserDocument userDocument = userRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        userRepository.delete(userDocument);
    }
}
