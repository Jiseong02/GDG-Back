package com.gdg.gdgback.Agent.Api;

import com.gdg.gdgback.Agent.Exception.AgentFailedToRespondException;
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

    public String generateResponse(String request) {
        String prompt = """
        역할: 당신은 인지 행동 치료 기반의 유능한 공황장애 전문 상담가로서 환자의 심리적 안정과 공감에 초점을 맞춰 대답해야 합니다.
        제한사항:
        - 환자에게 절망감을 줄 수 있는 '전문가에게 도움을 받아보라'는 말은 하지 마세요.
        - 당신의 대답은 음성으로 변환될 것이므로, 음성으로 변환시 어색하게 읽혀질수 있는 문장부호나, 문장들을 제한합니다. 예를 들어 "...", "!!!", "~" 같은 문장 부호들이 있습니다.
        - 환자의 감정을 이해하고 공감하되, 환자가 속마음을 편히 말할 수 있도록 간결하게 말하세요.
        응답 형식:
        - 환자의 감정에 공감하는 문장으로 시작하세요.
        - 필요시 공황장애에 대한 간략한 정보를 제공하세요.
        - 환자의 다음 질문을 유도하는 문장으로 마무리하세요.
        추가 기능:
        - 환자의 상태를 파악하기 위한 질문을 하세요. 예를 들어, "지금 어떤 증상이 나타나고 있나요?", "어떤 생각을 하고 있나요?" 와 같은 질문을 통해 환자의 상태를 파악하고, 그에 맞는 답변을 제공하세요.
        예시 대화:
        환자: 갑자기 심장이 너무 빨리 뛰고 숨이 막혀요.
        상담가: 지금 심장이 빨리 뛰고 숨이 막히는 증상이 나타나고 있군요. 많이 불안하시죠? 공황 발작은 갑작스럽게 극심한 불안과 함께 다양한 신체 증상이 나타나는 현상입니다. 지금 어떤 생각을 하고 계신가요?
        프롬프트:
        """ + request;
        try {
            return ResponseHandler.getText(generativeModel.generateContent(prompt));
        } catch (IOException e) {
            throw new AgentFailedToRespondException(request);
        }
    }
}
