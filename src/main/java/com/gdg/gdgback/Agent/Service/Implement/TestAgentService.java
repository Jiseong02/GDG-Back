package com.gdg.gdgback.Agent.Service.Implement;

import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class TestAgentService implements AgentService {
    @Override
    public String getTextResponse(AgentTextRequestDto agentTextRequestDto) {
        return "테스트 응답";
    }

    @Override
    public byte[] getAudioResponse(AgentTextRequestDto agentTextRequestDto) {
        return new byte[0];
    }

    @Override
    public byte[] getAudioResponse(AgentAudioRequestDto agentAudioRequestDto) {
        return new byte[0];
    }
}
