package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.TextMessage;
import lombok.*;

@Getter
@Builder
public class TextMessageDto implements TextMessage {
    private String id;
    private String counselId;
    private String role;
    private String content;
}
