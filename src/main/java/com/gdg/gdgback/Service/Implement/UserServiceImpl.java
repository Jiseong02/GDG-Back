package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Document.UserDocument;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Domain.User;
import com.gdg.gdgback.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        ExceptUserExists(user);
        userRepository.save(UserDocument.of(user));
    }

    public Object getUserById(String id) {
        ExceptUserNotExists(id);
        return userRepository.findById(id);
    }

    public Object[] getUserList() {
        return userRepository.findAll().toArray();
    }

    public Object[] getUserChatByIdAndDate(String id, Date date) {

        // userRepository.findChatByIdAndDate(id, date)
        return new Object[0];
    }

    // exceptions
    private void ExceptUserExists(User user) throws IllegalArgumentException {
        if(userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
    }

    private void ExceptUserNotExists(String id) throws IllegalArgumentException {
        if(userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
    }
}
