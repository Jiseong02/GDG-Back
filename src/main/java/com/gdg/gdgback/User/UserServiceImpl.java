package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.*;
import com.gdg.gdgback.User.DTO.Response.*;
import com.gdg.gdgback.User.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserCreateRequestDto userCreateRequestDto) {
        validateUserNotExists(userCreateRequestDto.getId());

        UserDocument userDocument = UserMapper.map(userCreateRequestDto);

        userRepository.save(userDocument);
    }

    @Override
    public UserReadResponseDto readUser(String id) {
        UserDocument userDocument = userRepository.findById(id)
                .orElseThrow(() -> new UserNotExistsException(id));

        return UserMapper.map(userDocument);
    }

    @Override
    public UserReadListResponseDto readUserList() {
        List<UserDocument> userDocumentList = userRepository.findAll();

        return UserMapper.map(userDocumentList);
    }

    @Override
    public void deleteUser(UserDeleteRequestDto deleteRequestDto) {
        UserDocument userDocument = userRepository.findById(deleteRequestDto.getId())
                .orElseThrow(() -> new UserNotExistsException(deleteRequestDto.getId()));

        userRepository.delete(userDocument);
    }

    @Override
    public void validateUserExists(String id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException(id);
        }
    }

    @Override
    public void validateUserNotExists(String id) {
        if(userRepository.existsById(id)) {
            throw new UserAlreadyExistsException();
        }
    }
}
