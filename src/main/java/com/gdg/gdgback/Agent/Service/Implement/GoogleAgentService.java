package com.gdg.gdgback.Agent.Service.Implement;

import com.gdg.gdgback.Agent.Api.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.TextRequestDto;
import com.gdg.gdgback.Agent.MessageDocument;
import com.gdg.gdgback.Agent.MessageRepository;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Agent.Service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("!test")
public class GoogleAgentService implements AgentService {
    private final GenerativeModelApi model;
    private final SpeechService speechService;

    private final MessageRepository messageRepository;

    @Autowired
    GoogleAgentService(GenerativeModelApi model, SpeechService speechService, MessageRepository messageRepository) {
        this.model = model;
        this.speechService = speechService;

        this.messageRepository = messageRepository;
    }

    @Override
    public String getTextResponse(TextRequestDto textRequestDto) throws IOException {
        messageRepository.save(
                MessageDocument.builder()
                        .counselId(textRequestDto.getCounselId())
                        .role("user")
                        .content(textRequestDto.getContent())
                        .build()
        );

        String response = model.generateResponse(textRequestDto.getContent());
        messageRepository.save(
                MessageDocument.builder()
                        .counselId(textRequestDto.getCounselId())
                        .role("model")
                        .content(response)
                        .build()
        );

        return response;
    }

    @Override
    public byte[] getAudioResponse(TextRequestDto textRequestDto) throws IOException {
        String textResponse = getTextResponse(textRequestDto);

        return speechService.textToSpeech(textResponse);
    }

    @Override
    public byte[] getAudioResponse(AudioRequestDto audioRequestDto) throws IOException {
        TextRequestDto textRequestDto = TextRequestDto.builder()
                .counselId(audioRequestDto.getCounselId())
                .content(speechService.speechToText(audioRequestDto.getContent()))
                .build();

        String textResponse = getTextResponse(textRequestDto);

        return speechService.textToSpeech(textResponse);
    }
}