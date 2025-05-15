package com.gdg.gdgback.Agent.Core;

import com.gdg.gdgback.Agent.Exception.AgentFailedToRespondException;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@Profile("!test")
public class GenerativeModelApi {
    private final GenerativeModel generativeModel;
    private final GenerativeModel generativeModelInJapan;

    @Autowired
    GenerativeModelApi() {
        String projectId = "fair-backbone-449407-u7";
        this.generativeModel = new GenerativeModel("gemini-1.5-flash-001", new VertexAI(projectId, "asia-northeast3"));
        this.generativeModel.withSafetySettings(Collections.singletonList(
                SafetySetting.newBuilder()
                        .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                        .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_NONE).build())
        );
        this.generativeModelInJapan = new GenerativeModel("gemini-1.5-flash-001", new VertexAI(projectId, "asia-northeast1"));
    }

    public String generateResponse(String prompt) {
        try {
            return ResponseHandler.getText(generativeModel.generateContent(prompt));
        } catch (IOException e) {
            throw new AgentFailedToRespondException(prompt);
        }
    }

    public String generateResponseInJapan(String prompt) {
        try {
            return ResponseHandler.getText(generativeModelInJapan.generateContent(prompt));
        } catch (IOException e) {
            throw new AgentFailedToRespondException(prompt);
        }
    }
}
