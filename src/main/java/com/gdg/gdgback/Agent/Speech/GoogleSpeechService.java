package com.gdg.gdgback.Agent.Speech;

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
    public byte[] textToSpeech(String text) {
        return textToSpeechApi.textToSpeech(text);
    }

    @Override
    public String speechToText(byte[] speech) {
        return speechToTextApi.speechToText(speech);
    }
}
