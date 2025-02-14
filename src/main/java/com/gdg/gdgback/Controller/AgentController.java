package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.AgentChatRequestDto;
import com.gdg.gdgback.Service.AgentService;
import com.google.api.Http;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    AgentService agentService;

    @PostMapping("/text")
    ResponseEntity<String> getTextReply(@RequestBody AgentChatRequestDto agentChatRequestDto) throws IOException {
        return ResponseEntity.ok().body(agentService.getTextReply(agentChatRequestDto));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceReply(@RequestBody AgentChatRequestDto agentChatRequestDto) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(Base64.getEncoder().encode(agentService.getVoiceReply(agentChatRequestDto).toByteArray()));
    }
}
