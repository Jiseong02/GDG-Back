package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface UserService {
     void addUser(User user) throws IllegalArgumentException;
     Object getUserById(String id) throws IllegalArgumentException;
     Object[] getUserList();
     Object[] getUserChatByIdAndDate(String id, Date date);
}
