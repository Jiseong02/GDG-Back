package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.AgentChatRequestDto;
import com.gdg.gdgback.Service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    AgentService agentService;

    @PostMapping
    ResponseEntity<String> chat(@RequestBody AgentChatRequestDto agentChatRequestDto) throws IOException {
        return ResponseEntity.ok().body(agentService.invoke(agentChatRequestDto));
    }
}
