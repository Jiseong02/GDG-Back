package com.gdg.gdgback.Agent.Service;

import com.gdg.gdgback.Agent.DTO.Request.AudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.TextRequestDto;

import java.io.IOException;

public interface AgentService {
    String getTextResponse(TextRequestDto textRequestDto) throws IOException;

    byte[] getAudioResponse(TextRequestDto textRequestDto) throws IOException;
    byte[] getAudioResponse(AudioRequestDto audioRequestDto) throws IOException;
}
