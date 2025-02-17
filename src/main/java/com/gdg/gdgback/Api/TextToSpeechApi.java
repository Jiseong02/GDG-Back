package com.gdg.gdgback.Api;

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

    public byte[] textToSpeech(String text) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
        return textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).toByteArray();
    }
}
