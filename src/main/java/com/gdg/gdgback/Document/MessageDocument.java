package com.gdg.gdgback.Document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "chat")
public class MessageDocument {
    @Id
    String id;
    String userId;
    String ConversationId;

    String role;
    String text;
}
