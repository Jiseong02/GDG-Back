package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Prompt;

import java.io.IOException;

public interface AgentService {
    String getTextReply(Prompt prompt) throws IOException;
    byte[] getVoiceReply(Prompt prompt) throws IOException;
}
