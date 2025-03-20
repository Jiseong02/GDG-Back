package com.gdg.gdgback;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;

import static com.gdg.gdgback.GdgbackApplicationTests.*;


public class TestValidator implements Validator {
    public void validateUserExists(String id) {
        if(!id.equals(USERID)) {
            throw new UserNotExistsException(id);
        }
    }

    public void validateCounselExists(String id) {
        if(!id.equals(COUNSELID)) {
            throw new CounselNotExistsException(id);
        }
    }
}
