package com.gdg.gdgback.User.Exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String message) {
        super("존재하지 않는 사용자: " + message);
    }
}
