package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        if(userRepository.existsById(userCreateRequestDto.getId())) throw new UserAlreadyExistsException();

        UserDocument userDocument = UserMapper.map(userCreateRequestDto);

        userRepository.save(userDocument);
    }

    public UserReadResponseDto readUser(String id) throws UserNotExistsException {
        UserDocument userDocument = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistsException(id));

        return UserMapper.map(userDocument);
    }

    public UserReadListResponseDto readUserList() {
        List<UserDocument> userDocumentList = userRepository.findAll();

        return UserMapper.map(userDocumentList);
    }

    public void deleteUser(UserDeleteRequestDto deleteRequestDto) throws UserNotExistsException {
        UserDocument userDocument = userRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new UserNotExistsException(deleteRequestDto.getId()));

        userRepository.delete(userDocument);
    }
}
