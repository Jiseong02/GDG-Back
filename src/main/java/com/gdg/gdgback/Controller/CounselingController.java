package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.PromptDto;
import com.gdg.gdgback.Service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/agent")
public class CounselingController {
    private final CounselingService counselingService;

    @Autowired
    CounselingController(CounselingService counselingService) {
        this.counselingService = counselingService;
    }

    @PostMapping("/text")
    ResponseEntity<String> getTextReply(@RequestBody PromptDto promptDto) throws IOException {
        return ResponseEntity.ok().body(counselingService.respondByText(promptDto));
    }
    @PostMapping("/voice")
    ResponseEntity<byte[]> getVoiceReply(@RequestBody PromptDto promptDto) throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(counselingService.respondByVoice(promptDto));
    }
}
