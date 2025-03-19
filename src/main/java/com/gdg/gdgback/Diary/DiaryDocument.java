package com.gdg.gdgback.Diary;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document("diary")
public class DiaryDocument {
    @Id
    String id;
    @Indexed
    String userId;
    String counselId;

    LocalDateTime date;
    byte[] picture;
    String[] category;
    int score;
    Boolean expected;
    String title;
    String content;
}
