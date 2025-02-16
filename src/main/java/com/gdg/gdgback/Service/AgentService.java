package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.AudioMessage;
import com.gdg.gdgback.Domain.Counsel;
import com.gdg.gdgback.Domain.TextMessage;

import java.io.IOException;

public interface AgentService {
    TextMessage getTextResponse(TextMessage message) throws IOException;
    AudioMessage getAudioResponse(TextMessage message) throws IOException;
    AudioMessage getAudioResponse(AudioMessage message) throws IOException;
    String generateCounsel(Counsel counsel);
}
