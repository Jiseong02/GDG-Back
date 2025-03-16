package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Message.DTO.MessageCreateRequestDto;

public class AgentMapper {
    public static MessageCreateRequestDto map(AgentTextRequestDto agentTextRequestDto) {
        return MessageCreateRequestDto.builder()
                .counselId(agentTextRequestDto.getCounselId())
                .role("user")
                .content(agentTextRequestDto.getContent())
                .build();
    }

    public static MessageCreateRequestDto map(String counselId, String content) {
        return MessageCreateRequestDto.builder()
                .counselId(counselId)
                .role("model")
                .content(content)
                .build();
    }
}
