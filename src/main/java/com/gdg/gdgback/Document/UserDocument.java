package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document("user")
public class UserDocument {
    @Id
    String id;
    String name;

    @CreatedDate
    LocalDateTime date;
}
