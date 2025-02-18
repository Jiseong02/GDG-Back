package com.gdg.gdgback.Exception;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super("존재하지 않는 사용자입니다.");
    }
}
