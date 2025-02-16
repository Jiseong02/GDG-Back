package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.TextMessageDto;
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

@RestController
@RequestMapping("/agent")
@Profile("!test")
public class CounselController {
    private final AgentService agentService;

    @Autowired
    CounselController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/text")
    ResponseEntity<TextMessageDto> getTextReply(@RequestBody TextMessageDto message) throws IOException {
        return ResponseEntity.ok().body(agentService.getTextResponse(message));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceReply(@RequestBody TextMessageDto prompt) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(agentService.getVoiceResponse(prompt));
    }
}
