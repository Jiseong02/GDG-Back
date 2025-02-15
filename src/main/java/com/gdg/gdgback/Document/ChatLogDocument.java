package com.gdg.gdgback.Document;

import com.gdg.gdgback.Domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "chat")
public class ChatLogDocument {
    @Id
    String id;
    String userId;
    Content[] contents;

    @AllArgsConstructor
    @Getter
    static public class Content implements Chat {
        String role;
        String text;
    }
}
