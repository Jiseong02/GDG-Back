package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
