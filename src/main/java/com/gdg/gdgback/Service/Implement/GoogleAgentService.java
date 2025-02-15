package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Api.GenerativeModelApi;
import com.gdg.gdgback.Domain.Prompt;
import com.gdg.gdgback.Service.AgentService;
import com.gdg.gdgback.Service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleAgentService implements AgentService {
    @Autowired
    private GenerativeModelApi model;
    @Autowired
    private SpeechService speechService;

    @Override
    public String getTextReply(Prompt prompt) throws IOException {
        return model.generateContent(prompt);
    }
    @Override
    public byte[] getVoiceReply(Prompt prompt) throws IOException {
        return speechService.TextToSpeech(getTextReply(prompt));
    }
}