package com.gdg.gdgback.Message.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageCreateRequestDto {
    public static MessageCreateRequestDto of(String counselId, String role, String content) {
        return new MessageCreateRequestDto(counselId, role, content);
    }

    String counselId;
    String role;
    String content;
}
