package com.gdg.gdgback.User.Exception;

public class UserAlreadyExistsException extends IllegalArgumentException {
    public UserAlreadyExistsException() {
        super("이미 존재하는 사용자입니다.");
    }
}
