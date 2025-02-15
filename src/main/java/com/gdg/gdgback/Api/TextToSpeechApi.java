package com.gdg.gdgback.Api;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;

import java.io.IOException;

@Component
public class TextToSpeechApi {
    private final VoiceSelectionParams voice;
    private final AudioConfig audioConfig;
    private final TextToSpeechClient textToSpeechClient;

    public TextToSpeechApi() throws IOException {
        voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR").setSsmlGender(SsmlVoiceGender.FEMALE).build();
        audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
        this.textToSpeechClient = TextToSpeechClient.create();
    }

    public ByteString synthesizeSpeech(String text) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
        return this.textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).getAudioContent();
    }
}
