package com.gdg.gdgback.Agent.Speech;

public interface SpeechService {
    byte[] textToSpeech(String text);
    String speechToText(byte[] speech);
}
