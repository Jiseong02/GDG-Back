package com.gdg.gdgback.Agent.Service;

public interface SpeechService {
    byte[] textToSpeech(String text);
    String speechToText(byte[] speech);
}
