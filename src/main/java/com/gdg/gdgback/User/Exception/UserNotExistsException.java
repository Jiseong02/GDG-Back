package com.gdg.gdgback.User.Exception;

public class UserNotExistsException extends Exception {
    public UserNotExistsException() {
        super("존재하지 않는 사용자입니다.");
    }
}
