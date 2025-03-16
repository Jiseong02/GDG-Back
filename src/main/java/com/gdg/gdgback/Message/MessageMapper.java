package com.gdg.gdgback.Message;

import com.gdg.gdgback.Message.DTO.MessageCreateRequestDto;

public class MessageMapper {
    public static MessageDocument map(MessageCreateRequestDto messageCreateRequestDto) {
        return MessageDocument.builder()
                .counselId(messageCreateRequestDto.getCounselId())
                .role(messageCreateRequestDto.getRole())
                .content(messageCreateRequestDto.getContent())
                .build();
    }
}
