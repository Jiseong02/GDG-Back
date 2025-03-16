package com.gdg.gdgback.Global;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselRepository;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private final UserRepository userRepository;
    private final CounselRepository counselRepository;

    @Autowired
    Validator(UserRepository userRepository, CounselRepository counselRepository) {
        this.userRepository = userRepository;
        this.counselRepository = counselRepository;
    }

    public void validateUserExists(String id) throws UserNotExistsException {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException(id);
        }
    }

    public void validateCounselExists(String id) throws CounselNotExistsException {
        if(!counselRepository.existsById(id)) {
            throw new CounselNotExistsException(id);
        }
    }
}
