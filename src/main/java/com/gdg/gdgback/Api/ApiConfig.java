package com.gdg.gdgback.Api;

import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile("default")
public class ApiConfig {
    @Bean
    public GenerativeModel generativeModel() {
        String modelName = "gemini-1.5-flash-001";
        VertexAI vertexAI = new VertexAI("fair-backbone-449407-u7", "asia-northeast3");
        return new GenerativeModel(modelName, vertexAI);
    }
    @Bean
    public TextToSpeechClient textToSpeechClient() throws IOException {
        return TextToSpeechClient.create();
    }
}
