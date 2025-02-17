package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Api.SpeechToTextApi;
import com.gdg.gdgback.Api.TextToSpeechApi;
import com.gdg.gdgback.DTO.AudioMessageDto;
import com.gdg.gdgback.DTO.TextMessageDto;
import com.gdg.gdgback.Domain.AudioMessage;
import com.gdg.gdgback.Domain.TextMessage;
import com.gdg.gdgback.Service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class GoogleSpeechService implements SpeechService {
    private final TextToSpeechApi textToSpeechApi;
    private final SpeechToTextApi speechToTextApi;

    @Autowired
    GoogleSpeechService(TextToSpeechApi textToSpeechApi, SpeechToTextApi speechToTextApi) {
        this.textToSpeechApi = textToSpeechApi;
        this.speechToTextApi = speechToTextApi;
    }

    @Override
    public AudioMessage textToSpeech(TextMessage message) {
        String text = message.getContent();
        byte[] speech = textToSpeechApi.textToSpeech(text);
        return AudioMessageDto.builder()
                .counselId(message.getCounselId())
                .role(message.getRole())
                .content(speech)
                .build();
    }

    @Override
    public TextMessage speechToText(AudioMessage message) {
        byte[] speech = message.getContent();
        String text = speechToTextApi.speechToText(speech);
        return TextMessageDto.builder()
                .counselId(message.getCounselId())
                .role(message.getRole())
                .content(text)
                .build();
    }
}
