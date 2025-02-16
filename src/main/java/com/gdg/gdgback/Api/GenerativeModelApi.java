package com.gdg.gdgback.Api;

import com.gdg.gdgback.DTO.TextMessageDto;
import com.gdg.gdgback.Domain.TextMessage;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!test")
public class GenerativeModelApi {
    private final GenerativeModel generativeModel;

    @Autowired
    GenerativeModelApi() {
        String modelName = "gemini-1.5-flash-001";
        VertexAI vertexAI = new VertexAI("fair-backbone-449407-u7", "asia-northeast3");
        this.generativeModel = new GenerativeModel(modelName, vertexAI);
    }

    public TextMessage generateMessage(TextMessage message) throws IOException {
        String content = ResponseHandler.getText(generativeModel.generateContent(message.getContent()));
        return TextMessageDto.builder()
                .counselId(message.getCounselId())
                .role("model")
                .content(content)
                .build();
    }
}
