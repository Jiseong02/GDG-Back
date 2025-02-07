package com.gdg.gdgback.Service;

import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        ExceptUserIdExists(user);
        userRepository.save(UserDocument.of(user));
    }

    // exceptions
    private void ExceptUserIdExists(User user) throws IllegalArgumentException {
        if(userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
    }
}
