package com.gdg.gdgback.Agent.Service;

import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;

import java.io.IOException;

public interface AgentService {
    String getTextResponse(AgentTextRequestDto agentTextRequestDto);
    byte[] getAudioResponse(AgentTextRequestDto agentTextRequestDto);
    byte[] getAudioResponse(AgentAudioRequestDto agentAudioRequestDto);
}
