package com.gdg.gdgback.Agent.Service;

import com.gdg.gdgback.Agent.Context.ContextService;
import com.gdg.gdgback.Agent.Context.DialogueEntry;
import com.gdg.gdgback.Agent.Core.GenerativeModelApi;
import com.gdg.gdgback.Agent.Context.Context;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Speech.SpeechService;
import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
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
    private final ContextService contextService;
    private final MessageService messageService;

    private final String DIRECTION = """
            [INSTRUCTION]
            You are a calm, empathetic therapist trained in CBT for panic disorder. Focus on emotionally stabilizing the patient using recent context.
            - Don’t mention professional help.
            - Keep tone soft, speech-natural, avoid awkward punctuation.
            - Always validate emotions.
            - If helpful, explain panic in simple terms.
            - End with a reflective, gentle question.
            
            [EXAMPLE]
            Patient: My heart suddenly started racing and I can't breathe.
            Therapist: That sounds frightening. Sudden heart racing and breathlessness are common in panic attacks, which can feel intense but aren’t dangerous. What’s going through your mind right now?
            """;

    @Autowired
    GoogleAgentService(GenerativeModelApi model, SpeechService speechService, ContextService contextService, MessageService messageService) {
        this.model = model;
        this.speechService = speechService;
        this.contextService = contextService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public String replyByText(HttpSession session, AgentTextRequestDto agentTextRequestDto) {
        String counselId = agentTextRequestDto.getCounselId();
        String userMessage = agentTextRequestDto.getContent();

        Context context = contextService.getContext(session);
        String prompt = DIRECTION + "\n\n" + context.toString() + "\n\n" + userMessage;

        String response = model.generateResponse(prompt);

        messageService.createMessage(MessageCreateRequestDto.of(counselId, "user", userMessage));
        messageService.createMessage(MessageCreateRequestDto.of(counselId, "model", response));

        contextService.updateContext(session, DialogueEntry.of(userMessage, response));

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
}