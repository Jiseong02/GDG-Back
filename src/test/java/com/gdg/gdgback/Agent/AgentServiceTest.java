package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.Api.GenerativeModelApi;
import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.Implement.GoogleAgentService;
import com.gdg.gdgback.Agent.Service.Implement.GoogleSpeechService;
import com.gdg.gdgback.Agent.Service.SpeechService;
import com.gdg.gdgback.Message.MessageService;
import com.gdg.gdgback.Message.MessageServiceImpl;
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

    @InjectMocks
    GoogleAgentService agentService;

    @BeforeEach
    void setUp() {
        doReturn("test").when(generativeModelApi).generateResponse(anyString());
        doReturn("test").when(speechService).speechToText(any(byte[].class));
        doReturn(new byte[5]).when(speechService).textToSpeech(anyString());
    }

    @Test
    void getTextResponse() {
        AgentTextRequestDto dto = AgentTextRequestDto.builder()
                .counselId("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->agentService.getTextResponse(dto));
    }
    @Test
    void getAudioResponseFromText() {
        AgentTextRequestDto dto = AgentTextRequestDto.builder()
                .counselId("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->agentService.getAudioResponse(dto));
    }
    @Test
    void getAudioResponseFromAudio() {
        AgentAudioRequestDto dto = AgentAudioRequestDto.builder()
                .counselId("test")
                .content(new byte[5])
                .build();
        Assertions.assertDoesNotThrow(()->agentService.getAudioResponse(dto));
    }
}
