package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.Context.Context;
import com.gdg.gdgback.Agent.Context.ContextService;
import com.gdg.gdgback.Agent.Core.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.GoogleAgentService;
import com.gdg.gdgback.Agent.Speech.GoogleSpeechService;
import com.gdg.gdgback.Agent.Speech.SpeechService;
import com.gdg.gdgback.Message.MessageService;
import com.gdg.gdgback.Message.MessageServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class AgentServiceTest {
    GenerativeModelApi generativeModelApi = mock(GenerativeModelApi.class);
    SpeechService speechService = mock(GoogleSpeechService.class);
    MessageService messageService = mock(MessageServiceImpl.class);
    ContextService contextService = mock(ContextService.class);
    HttpSession session = mock(HttpSession.class);

    @InjectMocks
    GoogleAgentService agentService;

    @BeforeEach
    void setUp() {
        doReturn("test").when(generativeModelApi).generateResponse(anyString());
        doReturn("test").when(speechService).speechToText(any(byte[].class));
        doReturn(new byte[5]).when(speechService).textToSpeech(anyString());
        Context testContext = Context.builder()
                .summary("")
                .history(new ArrayList<>())
                .build();
        doReturn(testContext).when(contextService).getContext(session);
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
}
