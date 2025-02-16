package com.gdg.gdgback.Api;

import com.gdg.gdgback.DTO.AudioMessageDto;
import com.gdg.gdgback.DTO.TextMessageDto;
import com.gdg.gdgback.Domain.AudioMessage;
import com.gdg.gdgback.Domain.TextMessage;
import com.google.cloud.texttospeech.v1.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!test")
public class TextToSpeechApi {
    private final VoiceSelectionParams voice;
    private final AudioConfig audioConfig;
    private final TextToSpeechClient textToSpeechClient;

    TextToSpeechApi() throws IOException {
        this.voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR").setSsmlGender(SsmlVoiceGender.FEMALE).build();
        this.audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
        this.textToSpeechClient = TextToSpeechClient.create();
    }

    public AudioMessage textToSpeech(TextMessage message) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(message.getContent()).build();
        byte[] audio = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).toByteArray();
        return AudioMessageDto.builder()
                .counselId(message.getCounselId())
                .role(message.getRole())
                .content(audio)
                .build();
    }
}
