package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document(collection = "user")
public class UserDocument {
    @Id
    private String id;
    private String name;

    @CreatedDate
    private LocalDateTime date;
}
