package com.gdg.gdgback.Counsel;

public class CounselNotExistsException extends RuntimeException {
    public CounselNotExistsException(String message) {
        super("Counsel does not exist: " + message);
    }
}
