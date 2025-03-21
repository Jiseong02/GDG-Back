package com.gdg.gdgback;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;

public class TestValidator implements Validator {
    public void validateUserExists(String id) {
        if(!id.equals("test")) {
            throw new UserNotExistsException(id);
        }
    }

    public void validateCounselExists(String id) {
        if(!id.equals("test")) {
            throw new CounselNotExistsException(id);
        }
    }
}
