package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.UserCreationDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface UserService {
     void addUser(UserCreationDto userCreationDto);
     Object getUserById(String id);
     Object[] getUserList();
     Object[] getUserChatByIdAndDate(String id, Date date);
}
