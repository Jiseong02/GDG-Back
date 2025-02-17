package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.TextRequestDto;
import com.gdg.gdgback.Service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    ResponseEntity<String> getTextResponse(@RequestBody TextRequestDto message) throws IOException {
        return ResponseEntity.ok().body(agentService.getTextResponse(message));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceResponse(@RequestBody TextRequestDto message) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.getAudioResponse(message));
    }
}
