package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;
import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.Exception.UserNotFoundException;
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

    public void createUser(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        if(userRepository.existsById(userCreateRequestDto.getId())) {
            throw new UserAlreadyExistsException();
        }

        UserDocument userDocument = UserDocument.builder()
                .id(userCreateRequestDto.getId())
                .name(userCreateRequestDto.getName())
                .build();

        userRepository.save(userDocument);
    }

    public UserReadResponseDto readUser(String id) throws UserNotFoundException{
        UserDocument userDocument = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

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

        return UserReadListResponseDto.builder()
                .users(users)
                .build();
    }

    @Override
    public void deleteUser(UserDeleteRequestDto deleteRequestDto) throws UserNotFoundException {
        UserDocument userDocument = userRepository.findById(deleteRequestDto.getId())
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(userDocument);
    }
}
