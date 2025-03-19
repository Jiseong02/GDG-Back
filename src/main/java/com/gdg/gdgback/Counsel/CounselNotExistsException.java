package com.gdg.gdgback.Counsel;

public class CounselNotExistsException extends RuntimeException {
    public CounselNotExistsException(String message) {
        super("존재하지 않는 상담: " + message);
    }
}
