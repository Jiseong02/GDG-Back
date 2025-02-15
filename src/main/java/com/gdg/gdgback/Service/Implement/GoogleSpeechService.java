package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Service.SpeechService;
import com.google.cloud.texttospeech.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("auth")
public class GoogleSpeechService implements SpeechService {
    private final TextToSpeechClient textToSpeechClient;

    private final VoiceSelectionParams voice;
    private final AudioConfig audioConfig;

    @Autowired
    GoogleSpeechService(TextToSpeechClient textToSpeechClient) {
        this.voice = VoiceSelectionParams.newBuilder().setLanguageCode("ko-KR").setSsmlGender(SsmlVoiceGender.FEMALE).build();
        this.audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();
        this.textToSpeechClient = textToSpeechClient;
    }

    @Override
    public byte[] TextToSpeech(String text) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
        return textToSpeechClient.synthesizeSpeech(input, voice, audioConfig).toByteArray();
    }
}
