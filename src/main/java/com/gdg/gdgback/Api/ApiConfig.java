package com.gdg.gdgback.Api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ApiConfig {
    @Bean
    public GenerativeModelApi generativeModelApi() {
        return new GenerativeModelApi();
    }
    @Bean
    public TextToSpeechApi textToSpeechApi() throws IOException {
        return new TextToSpeechApi();
    }
}
