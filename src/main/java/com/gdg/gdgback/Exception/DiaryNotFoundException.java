package com.gdg.gdgback.Exception;

public class DiaryNotFoundException extends IllegalArgumentException {
    public DiaryNotFoundException() {
        super("존재하지 않는 일지입니다.");
    }
}
