package com.gdg.gdgback.Message;

import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadListResponseDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageMapper {
    public static MessageDocument map(MessageCreateRequestDto messageCreateRequestDto) {
        return MessageDocument.builder()
                .counselId(messageCreateRequestDto.getCounselId())
                .role(messageCreateRequestDto.getRole())
                .content(messageCreateRequestDto.getContent())
                .build();
    }
    public static MessageReadResponseDto map(MessageDocument messageDocument) {
        return MessageReadResponseDto.builder()
                .id(messageDocument.getId())
                .counselId(messageDocument.getCounselId())
                .role(messageDocument.getRole())
                .content(messageDocument.getContent())
                .date(messageDocument.getDate())
                .build();
    }

    public static MessageReadListResponseDto map(List<MessageDocument> messageDocumentList) {
        ArrayList<MessageReadResponseDto> messages = messageDocumentList.stream().map(MessageMapper::map).collect(Collectors.toCollection(ArrayList::new));

        return MessageReadListResponseDto.builder()
                .messages(messages)
                .build();
    }
}
