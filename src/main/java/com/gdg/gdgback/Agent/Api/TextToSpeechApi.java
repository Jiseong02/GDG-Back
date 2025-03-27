package com.gdg.gdgback.Agent.Api;

import com.gdg.gdgback.Agent.Exception.AgentFailedToCreateTTSError;
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

    TextToSpeechApi() {
        this.voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR").setSsmlGender(SsmlVoiceGender.FEMALE).build();
        this.audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
        try {
            this.textToSpeechClient = TextToSpeechClient.create();
        } catch (IOException e) {
            throw new AgentFailedToCreateTTSError();
        }
    }

    public byte[] textToSpeech(String text) {
        SynthesisInput input = SynthesisInput.newBuilder()
                .setText(text)
                .build();
        return textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).toByteArray();
    }
}
