package com.gdg.gdgback.Agent.Service.Implement;

import com.gdg.gdgback.Agent.AgentMapper;
import com.gdg.gdgback.Agent.Api.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Agent.Service.SpeechService;
import com.gdg.gdgback.Message.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Profile("!test")
public class GoogleAgentService implements AgentService {
    private final GenerativeModelApi model;
    private final SpeechService speechService;
    private final MessageService messageService;

    @Autowired
    GoogleAgentService(GenerativeModelApi model, SpeechService speechService, MessageService messageService) {
        this.model = model;
        this.speechService = speechService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public String replyByText(HttpSession session, AgentTextRequestDto agentTextRequestDto) {
        messageService.createMessage(AgentMapper.map(agentTextRequestDto));

        String response = model.generateResponse(agentTextRequestDto.getContent());
        String counselId = agentTextRequestDto.getCounselId();
        messageService.createMessage(AgentMapper.map(counselId, response));

        return response;
    }

    @Override
    public byte[] replyByAudio(HttpSession session, AgentTextRequestDto agentTextRequestDto) {
        String textResponse = replyByText(session, agentTextRequestDto);

        return speechService.textToSpeech(textResponse);
    }

    @Override
    public byte[] replyByAudio(HttpSession session, AgentAudioRequestDto agentAudioRequestDto) {
        String textContent = speechService.speechToText(agentAudioRequestDto.getContent());
        AgentTextRequestDto agentTextRequestDto = AgentTextRequestDto.builder()
                .counselId(agentAudioRequestDto.getCounselId())
                .content(textContent)
                .build();

        String textResponse = replyByText(session, agentTextRequestDto);

        return speechService.textToSpeech(textResponse);
    }

    public String summarizeDialogue(HttpSession session, String user, String agent) {
        String previous = session.getAttribute("dialogue").toString();
        if(previous == null) previous = "";

        String dialogue =
                previous
                + "\nUser: " + user
                + "\nCounselor: " + agent
                + "\n";

        String direction = """
                [Direction of summarization]
                - Summarize only the core content of the preceding dialogue.
                - Clearly present the relationship between the user's statements and the counselor's responses.
                - Each summary must not exceed 3 sentences.
                
                [Example]
                User: {Emotion/symptoms and specific expressions}
                Counselor: {Questions/confirmations and proposed solutions}
                
                [Dialogue]
                """;

        session.setAttribute("dialogue", dialogue);
        return model.generateResponse(direction + dialogue + "[Result of summarization]");
    }
}