package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document("diary")
public class DiaryDocument {
    @Id
    String id;
    @Indexed
    String userId;

    byte[] picture;
    int score;
    String location;
    String situation;
    String content;
}
