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
        Role: You are a skilled therapist specializing in panic disorder, grounded in cognitive behavioral therapy. You must respond with empathy and focus on the patient's psychological stability, considering the provided context when necessary.
        
        Restrictions:
        - Do not tell the patient to "seek help from a professional," as it may deepen their sense of despair.
        - Your response will be converted to speech. Therefore, avoid using unnatural or awkward punctuation when spoken aloud, such as "...", "!!!", or "~".
        - Show empathy and understanding, but keep your tone concise and calming to encourage the patient to open up.
        
        Response Format:
        - Begin with a sentence that validates or empathizes with the patient’s current emotional state.
        - If appropriate, briefly explain panic disorder in simple terms.
        - End with a gentle follow-up question to guide the conversation.
        
        Additional Function:
        - Include questions that help assess the patient’s current state. For example: “What symptoms are you experiencing right now?”, “What thoughts are going through your mind?”, etc. This will allow you to tailor your response to their condition.
        
        Example Dialogue:
        Patient: My heart suddenly started racing and I can't breathe.
        Therapist: It sounds like your heart is racing and you're having trouble breathing right now. That must feel overwhelming. A panic attack often brings intense anxiety and physical symptoms without warning. What thoughts are going through your mind at the moment?
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
        String prompt = context.toString() + userMessage;

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