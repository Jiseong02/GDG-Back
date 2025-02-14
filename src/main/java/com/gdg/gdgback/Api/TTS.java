package com.gdg.gdgback.Api;

import com.google.cloud.texttospeech.v1.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TTS {
    private final VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
            .setLanguageCode("ko-KR")
            .setSsmlGender(SsmlVoiceGender.FEMALE)
            .build();
    private final AudioConfig audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
    private final TextToSpeechClient textToSpeechClient;

    public TTS() throws IOException {
        this.textToSpeechClient = TextToSpeechClient.create();
    }

    public SynthesizeSpeechResponse synthesizeSpeech(String text) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
        return this.textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
    }
}
