package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document("counsel")
public class CounselDocument {
    @Id
    String id;
    @Indexed
    String userId;

    @CreatedDate
    LocalDateTime date;
    int seconds;
    String summation;
}
