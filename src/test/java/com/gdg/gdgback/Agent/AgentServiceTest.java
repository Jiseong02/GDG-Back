package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.Api.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.Implement.GoogleAgentService;
import com.gdg.gdgback.Agent.Service.Implement.GoogleSpeechService;
import com.gdg.gdgback.Agent.Service.SpeechService;
import com.gdg.gdgback.Message.MessageService;
import com.gdg.gdgback.Message.MessageServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {
    GenerativeModelApi generativeModelApi = mock(GenerativeModelApi.class);
    SpeechService speechService = mock(GoogleSpeechService.class);
    MessageService messageService = mock(MessageServiceImpl.class);
    HttpSession session = mock(HttpSession.class);

    @InjectMocks
    GoogleAgentService agentService;

    @BeforeEach
    void setUp() {
        doReturn("test").when(generativeModelApi).generateResponse(anyString());
        doReturn("test").when(speechService).speechToText(any(byte[].class));
        doReturn(new byte[5]).when(speechService).textToSpeech(anyString());
        String testDialogue = """
                [Dialogue]
                User: My hands are trembling and my chest feels tight. I feel like something terrible is going to happen.
                Counselor: You're describing classic symptoms of a panic attack. Try to focus on your breathing — slow and deep inhales.
                
                User: I can't stop thinking I'm going to faint or lose control.
                Counselor: That fear is very common. You’re not in danger, even if it feels overwhelming. Let’s try grounding exercises together.
                
                User: What if this never stops?
                Counselor: I hear how scared you are. These episodes always pass, and we’ll work together on ways to reduce their frequency.
                
                User: I feel ashamed that I can't control this.
                Counselor: There’s nothing to be ashamed of. You're doing the right thing by talking about it and seeking help.
                """;
        doReturn(testDialogue).when(session).getAttribute("dialogue");
    }

    @Test
    void replyByText() {
        AgentTextRequestDto dto = AgentTextRequestDto.builder()
                .counselId("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->agentService.replyByText(session, dto));
    }
    @Test
    void replyByAudioFromText() {
        AgentTextRequestDto dto = AgentTextRequestDto.builder()
                .counselId("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->agentService.replyByAudio(session, dto));
    }
    @Test
    void replyByAudioResponseFromAudio() {
        AgentAudioRequestDto dto = AgentAudioRequestDto.builder()
                .counselId("test")
                .content(new byte[5])
                .build();
        Assertions.assertDoesNotThrow(()->agentService.replyByAudio(session, dto));
    }
    @Test
    void summarizeDialogue() {
        Assertions.assertDoesNotThrow(()->agentService.summarizeDialogue(session, "Can i really survive in this hell?", "As you've always done. I'm very sure you can do it."));
    }
}
