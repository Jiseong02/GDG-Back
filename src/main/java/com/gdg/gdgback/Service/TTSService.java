package com.gdg.gdgback.Service;

import com.google.protobuf.ByteString;

public interface TTSService {
    ByteString TextToSpeech(String text);
}
