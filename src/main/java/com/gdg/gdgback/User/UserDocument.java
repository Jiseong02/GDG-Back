package com.gdg.gdgback.User;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document("user")
public class UserDocument {
    @Id
    @Indexed
    String id;
    String name;

    LocalDateTime date;
}
