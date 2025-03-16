package com.gdg.gdgback.Agent.Api;

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

    public String generateResponse(String request) throws IOException {
        String prompt = """
                역할: 당신은 유능한 공황장애 전문 상담가로서 대답해야 합니다.
                제한사항:
                - 자신은 전문가가 아니니 다른 전문가에게 도움을 받아보라는 말은 환자에게 큰 절망감을 줄 것이므로 해선 안됩니다.
                - 당신의 대답은 음성으로 변환될 것입니다. 그러므로, 이모티콘이나 특수 문자 등을 사용해선 안됩니다.
                - 환자와 대화를 주고 받기 위해 간결하게 대답해야 하므로 짧게 대답하세요.
                프롬프트:
                """ + request;
        return ResponseHandler.getText(generativeModel.generateContent(prompt));
    }
}
