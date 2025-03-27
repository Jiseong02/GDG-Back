package com.gdg.gdgback.User.Exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String message) {
        super("User does not exist: " + message);
    }
}
