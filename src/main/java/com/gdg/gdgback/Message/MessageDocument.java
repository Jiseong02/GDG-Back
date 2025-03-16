package com.gdg.gdgback.Message;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "message")
public class MessageDocument {
    @Id
    String id;
    @Indexed
    String counselId;

    String role;
    String content;

    @CreatedDate
    LocalDateTime date;
}
