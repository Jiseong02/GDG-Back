package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     void addUser(User user);
}
