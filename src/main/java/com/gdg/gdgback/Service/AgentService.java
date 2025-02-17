package com.gdg.gdgback.Service;

import com.gdg.gdgback.DTO.Request.AudioRequestDto;
import com.gdg.gdgback.DTO.Request.TextRequestDto;

import java.io.IOException;

public interface AgentService {
    String getTextResponse(TextRequestDto textRequestDto) throws IOException;

    byte[] getAudioResponse(TextRequestDto textRequestDto) throws IOException;
    byte[] getAudioResponse(AudioRequestDto audioRequestDto) throws IOException;
}
