package com.gdg.gdgback.Agent.Service.Implement;

import com.gdg.gdgback.Agent.AgentMapper;
import com.gdg.gdgback.Agent.Api.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Agent.Service.SpeechService;
import com.gdg.gdgback.Message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Lazy
@Service
@Profile("!test")
public class GoogleAgentService implements AgentService {
    private final GenerativeModelApi model;
    private final SpeechService speechService;
    private final MessageService messageService;

    @Autowired
    GoogleAgentService(GenerativeModelApi model, SpeechService speechService, MessageService messageService) {
        this.model = model;
        this.speechService = speechService;
        this.messageService = messageService;
    }

    @Override
    public String getTextResponse(AgentTextRequestDto agentTextRequestDto) throws IOException {
        MessageCreateRequestDto message = AgentMapper.map(agentTextRequestDto);
        messageService.createMessage(message);

        String response = model.generateResponse(agentTextRequestDto.getContent());

        String counselId = agentTextRequestDto.getCounselId();
        messageService.createMessage(AgentMapper.map(counselId, response));

        return response;
    }

    @Override
    public byte[] getAudioResponse(AgentTextRequestDto agentTextRequestDto) throws IOException {
        String textResponse = getTextResponse(agentTextRequestDto);

        return speechService.textToSpeech(textResponse);
    }

    @Override
    public byte[] getAudioResponse(AgentAudioRequestDto agentAudioRequestDto) throws IOException {
        String textContent = speechService.speechToText(agentAudioRequestDto.getContent());
        AgentTextRequestDto agentTextRequestDto = AgentTextRequestDto.builder()
                .counselId(agentAudioRequestDto.getCounselId())
                .content(textContent)
                .build();

        String textResponse = getTextResponse(agentTextRequestDto);

        return speechService.textToSpeech(textResponse);
    }
}