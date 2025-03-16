package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.*;
import com.gdg.gdgback.User.DTO.Response.*;
import com.gdg.gdgback.User.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        validateUserNotExists(userCreateRequestDto.getId());

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

    public void validateUserExists(String id) throws UserNotExistsException {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException(id);
        }
    }

    public void validateUserNotExists(String id) throws UserAlreadyExistsException {
        if(userRepository.existsById(id)) {
            throw new UserAlreadyExistsException();
        }
    }
}
