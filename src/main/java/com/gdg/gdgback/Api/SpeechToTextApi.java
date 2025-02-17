package com.gdg.gdgback.Api;

import com.google.cloud.speech.v2.*;
import com.google.protobuf.ByteString;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!test")
public class SpeechToTextApi {
    static final private String recognizer = "gdg-recognizer";
    static private SpeechClient speechClient;

    SpeechToTextApi() throws IOException {
        speechClient = SpeechClient.create();
    }

    public String speechToText(byte[] speech) {
        RecognizeRequest request = RecognizeRequest.newBuilder()
                .setRecognizer(recognizer)
                .setContent(ByteString.copyFrom(speech))
                .build();

        return speechClient.recognize(request).getResults(0).getAlternatives(0).getTranscript();
    }
}
