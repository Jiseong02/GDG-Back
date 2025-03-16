package com.gdg.gdgback.Agent.DTO.Request;

import lombok.*;

@Data
@Builder
public class AgentAudioRequestDto {
    private String counselId;
    private byte[] content;
}
