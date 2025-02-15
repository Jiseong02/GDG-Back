package com.gdg.gdgback.Api;

import com.gdg.gdgback.Domain.Prompt;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GenerativeModelApi {
    private final GenerativeModel generativeModel;

    GenerativeModelApi() {
        String modelName = "gemini-1.5-flash-001";
        VertexAI vertexAI = new VertexAI("fair-backbone-449407-u7", "asia-northeast3");
        this.generativeModel = new GenerativeModel(modelName, vertexAI);
    }
    public String generateContent(Prompt prompt) throws IOException {
        return ResponseHandler.getText(generativeModel.generateContent(prompt.getContent()));
    }
}

