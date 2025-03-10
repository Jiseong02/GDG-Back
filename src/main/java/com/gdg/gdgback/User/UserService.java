package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
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

    public void deleteUser(UserDeleteRequestDto deleteRequestDto) throws UserNotFoundException {
        UserDocument userDocument = userRepository.findById(deleteRequestDto.getId())
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(userDocument);
    }
}
