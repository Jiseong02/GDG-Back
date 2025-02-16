package com.gdg.gdgback.Document;

import com.gdg.gdgback.Domain.TextMessage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "message")
public class MessageDocument {
    @Id
    String id;
    @Indexed
    String CounselingId;

    String role;
    String content;

    public static MessageDocument of(TextMessage textMessage) {
        return MessageDocument.builder()
                .CounselingId(textMessage.getCounselId())
                .role(textMessage.getRole())
                .content(textMessage.getContent())
                .build();
    }
}
