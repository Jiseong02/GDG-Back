package com.gdg.gdgback.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@AllArgsConstructor
@Document(collection = "chat")
public class ChatDocument {
    @Id
    String id;
    Content[] contents;

    @Getter
    static private class Content {
        String role;
        String text;
    }
}
