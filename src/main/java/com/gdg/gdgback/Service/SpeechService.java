package com.gdg.gdgback.Service;

public interface SpeechService {
    byte[] textToSpeech(String text);
    String speechToText(byte[] speech);
}
