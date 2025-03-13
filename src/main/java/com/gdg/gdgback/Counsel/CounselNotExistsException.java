package com.gdg.gdgback.Counsel;

public class CounselNotExistsException extends IllegalArgumentException {
    public CounselNotExistsException() {
        super("존재하지 않는 상담입니다.");
    }
}
