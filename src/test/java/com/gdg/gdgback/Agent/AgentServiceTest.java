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
        Assertions.assertDoesNotThrow(()->agentService.summarizeDialogue(session, "hi", "hello, are you alright?"));
    }
}
