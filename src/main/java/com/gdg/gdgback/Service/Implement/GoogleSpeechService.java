package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Api.TextToSpeechApi;
import com.gdg.gdgback.Service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleSpeechService implements SpeechService {
    @Autowired
    private TextToSpeechApi textToSpeechApi;

    @Override
    public byte[] TextToSpeech(String text) {
        return textToSpeechApi.synthesizeSpeech(text).toByteArray();
    }
}
