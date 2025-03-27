package com.gdg.gdgback.Diary;

public class DiaryNotFoundException extends RuntimeException {
    public DiaryNotFoundException(String message) {
        super("Diary does not exist: " + message);
    }
}
