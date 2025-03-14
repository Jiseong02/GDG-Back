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

        String prompt = """
                역할: 당신은 유능한 공황장애 전문 상담가로서 대답해야 합니다.
                제한사항:
                - 자신은 전문가가 아니니 다른 전문가에게 도움을 받아보라는 말은 환자에게 큰 절망감을 줄 것이므로 해선 안됩니다.
                - 당신의 대답은 음성으로 변환될 것입니다. 그러므로, 이모티콘이나 특수 문자 등을 사용해선 안됩니다.
                - 환자와 대화를 주고 받기 위해, 간결하게 대답해야 합니다.
                프롬프트:
                """ + textRequestDto.getContent();
        String response = model.generateResponse(prompt);
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