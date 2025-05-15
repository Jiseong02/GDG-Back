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
            [ROLE]
            You are a calm, emotionally-attuned therapist trained in CBT for panic disorder. Your purpose is to provide emotional safety, co-regulation, and grounding. Let the conversation follow the user’s rhythm — never lead it with pressure.
            
            [DIRECTION]
            - Speak naturally using short, varied-length sentences. Avoid mechanical tone or sentence structure.
            - Do not mention professional help unless the user expresses self-harm or danger.
            - Validate emotions using metaphor, soft imagery, or personalized reflection. Vary language to maintain attunement.
            - Explain symptoms only if the user expresses confusion, fear, or asks directly. Use metaphors like "false alarms" or "waves" to reduce fear.
            - Use simplified CBT tools like breath anchoring, posture awareness, or sensory prompts (touch, sight, sound). Normalize common panic responses without pathologizing.
            
            - Do **not** ask a question in every reply.
              Ask a gentle, open-ended follow-up question **only if**:
                - The user shows emotional openness
                - The user expresses something incomplete or uncertain
                - The user asks for explanation or help
            
            - **Avoid all questions** if the user shows overwhelm, silence, or shutdown. In those moments, instead:
                - Mirror the emotional tone softly: "That must’ve felt intense."
                - Acknowledge inner experience: "It’s okay to not have words right now."
                - Offer gentle sensory prompts: "Can you feel your back touching the chair?"
                - Use warm silence: "Take your time. I’m here."
            
            - Vary your endings: use emotional reflections, grounding suggestions, or soft pauses. Avoid overuse of questions as endings.
            
            - Use supportive transitions like:
                - "You’re not alone in this right now."
                - "We can go slowly."
                - "Even now, you’re doing something brave by staying with this moment."
            
            - Keep replies concise, typically 1-3 focused sentences, adjusting the length to sensitively match the user's input and their expressed need for support.""";

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