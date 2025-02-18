package com.gdg.gdgback.Exception;

public class CounselNotFoundException extends IllegalArgumentException {
    public CounselNotFoundException() {
        super("존재하지 않는 상담입니다.");
    }
}
