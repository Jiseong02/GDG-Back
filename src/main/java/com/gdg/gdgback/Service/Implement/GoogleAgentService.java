package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Domain.Prompt;
import com.gdg.gdgback.Service.AgentService;
import com.gdg.gdgback.Service.SpeechService;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("default")
public class GoogleAgentService implements AgentService {
    private final GenerativeModel model;
    private final SpeechService speechService;

    @Autowired
    GoogleAgentService(GenerativeModel model, SpeechService speechService) {
        this.model = model;
        this.speechService = speechService;
    }

    @Override
    public String getTextReply(Prompt prompt) throws IOException {
        GenerateContentResponse response = model.generateContent(prompt.getContent());
        return ResponseHandler.getText(response);
    }
    @Override
    public byte[] getVoiceReply(Prompt prompt) throws IOException {
        return speechService.TextToSpeech(getTextReply(prompt));
    }
}