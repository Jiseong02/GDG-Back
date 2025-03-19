package com.gdg.gdgback.Diary;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@Document("diary")
public class DiaryDocument {
    @Id
    String id;
    @Indexed
    String userId;
    String counselId;

    Date date;
    byte[] picture;
    String[] category;
    int score;
    boolean expected;
    String title;
    String content;
}
