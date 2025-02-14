package com.gdg.gdgback.Service;

import com.gdg.gdgback.Api.TTS;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TTSServiceImpl implements TTSService{
    @Autowired
    private TTS tts;

    @Override
    public ByteString TextToSpeech(String text) {
        return tts.synthesizeSpeech(text).getAudioContent();
    }
}
