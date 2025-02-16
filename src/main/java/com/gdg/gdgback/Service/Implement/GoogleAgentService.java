package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Api.GenerativeModelApi;
import com.gdg.gdgback.Document.CounselDocument;
import com.gdg.gdgback.Document.MessageDocument;
import com.gdg.gdgback.Domain.AudioMessage;
import com.gdg.gdgback.Domain.Counsel;
import com.gdg.gdgback.Domain.TextMessage;
import com.gdg.gdgback.Repository.CounselRepository;
import com.gdg.gdgback.Repository.MessageRepository;
import com.gdg.gdgback.Service.AgentService;
import com.gdg.gdgback.Service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("!test")
public class GoogleAgentService implements AgentService {
    private final GenerativeModelApi model;
    private final SpeechService speechService;

    private final CounselRepository counselRepository;
    private final MessageRepository messageRepository;

    @Autowired
    GoogleAgentService(GenerativeModelApi model, SpeechService speechService, CounselRepository counselRepository, MessageRepository messageRepository) {
        this.model = model;
        this.speechService = speechService;

        this.counselRepository = counselRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public TextMessage getTextResponse(TextMessage message) throws IOException {
        messageRepository.save(MessageDocument.of(message));

        TextMessage responseMessage = model.generateMessage(message);
        messageRepository.save(MessageDocument.of(responseMessage));

        return responseMessage;
    }
    @Override
    public AudioMessage getAudioResponse(TextMessage message) throws IOException {
        TextMessage response = getTextResponse(message);
        return speechService.textToSpeech(response);
    }

    @Override
    public AudioMessage getAudioResponse(AudioMessage message) throws IOException {
        TextMessage text = speechService.speechToText(message);
        TextMessage response = getTextResponse(text);
        return speechService.textToSpeech(response);
    }

    @Override
    public String generateCounsel(Counsel counsel) {
        CounselDocument counselDocument = CounselDocument.builder()
                .userId(counsel.getUserId())
                .date(counsel.getDate())
                .build();
        return counselRepository.save(counselDocument).getId();
    }
}