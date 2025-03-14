package com.gdg.gdgback.Diary;

public class DiaryNotFoundException extends Exception {
    public DiaryNotFoundException() {
        super("존재하지 않는 일지입니다.");
    }
}
