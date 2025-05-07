package com.gdg.gdgback.Agent;

import com.gdg.gdgback.Agent.DTO.Request.AgentAudioRequestDto;
import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<String> getTextResponse(HttpSession session, @Valid @RequestBody AgentTextRequestDto agentTextRequestDto) {
        return ResponseEntity.ok().body(agentService.replyByText(session, agentTextRequestDto));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceResponse(HttpSession session, @Valid @RequestBody AgentTextRequestDto agentTextRequestDto) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.replyByAudio(session, agentTextRequestDto));
    }
    @PostMapping("/call")
    ResponseEntity<byte[]> getVoiceResponse(HttpSession session, @Valid @RequestBody AgentAudioRequestDto agentAudioRequestDto) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.replyByAudio(session, agentAudioRequestDto));
    }
}
