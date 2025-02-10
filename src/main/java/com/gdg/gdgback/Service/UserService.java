package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.UserCreationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     void addUser(UserCreationDto userCreationDto);
     Object getUserById(String id);
     Object[] getUserList();
}
