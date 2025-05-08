package com.gdg.gdgback.Diary;

public class DiaryImageIOException extends RuntimeException {
    public DiaryImageIOException(String message) {
        super("IO Exception of Diary Image: " + message);
    }
}
