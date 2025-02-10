package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.UserCreationDto;
import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(UserCreationDto user) {
        ExceptUserIdExists(user);
        UserDocument doc = UserDocument.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
        userRepository.save(doc);
    }

    public Object getUserById(String id) {
        ExceptUserIdNotExists(id);
        return userRepository.findById(id);
    }

    public Object[] getUserList() {
        return userRepository.findAll().toArray();
    }

    // exceptions
    private void ExceptUserIdExists(User user) throws IllegalArgumentException {
        if(userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
    }

    private void ExceptUserIdNotExists(String id) throws IllegalArgumentException {
        if(userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
    }
}
