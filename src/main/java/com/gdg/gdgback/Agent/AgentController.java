package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Lazy
@Profile("!test")
@RestController
@RequestMapping("/counsel/agent")
public class AgentController {
    private final AgentService agentService;

    @Autowired
    AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/text")
    ResponseEntity<String> getTextResponse(@Valid @RequestBody AgentTextRequestDto agentTextRequestDto) throws IOException {
        return ResponseEntity.ok().body(agentService.getTextResponse(agentTextRequestDto));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceResponse(@Valid @RequestBody AgentTextRequestDto agentTextRequestDto) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.getAudioResponse(agentTextRequestDto));
    }
    @PostMapping("/call")
    ResponseEntity<byte[]> getVoiceResponse(@Valid @RequestBody AgentAudioRequestDto agentAudioRequestDto) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.getAudioResponse(agentAudioRequestDto));
    }
}
