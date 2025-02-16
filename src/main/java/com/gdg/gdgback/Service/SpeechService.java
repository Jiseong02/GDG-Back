package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.AudioMessage;
import com.gdg.gdgback.Domain.TextMessage;

public interface SpeechService {
    AudioMessage textToSpeech(TextMessage message);
    TextMessage speechToText(AudioMessage message);
}
