package com.gdg.gdgback.Diary;

public class DiaryNotFoundException extends RuntimeException {
    public DiaryNotFoundException(String message) {
        super("존재하지 않는 일지: " + message);
    }
}
