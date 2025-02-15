package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@Document("panic")
public class PanicDocument {
    @Id
    String id;
    String userId;
    Date date;
    Diary diary;

    @Builder
    @Getter
    static public class Diary {
        byte[] picture;
        int score;
        int callTime;
        String location;
        String situation;
        String content;
    }
}
