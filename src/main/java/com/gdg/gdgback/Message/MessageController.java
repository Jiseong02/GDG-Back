package com.gdg.gdgback.Message;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Message.DTO.Response.MessageReadListResponseDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("!test")
@RestController
@RequestMapping("/counsel/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<MessageReadListResponseDto> readMessageByCounselId(@RequestParam String counselId) {
        return ResponseEntity.ok().body(messageService.readMessageByCounselId(counselId));
    }

    @GetMapping("/id")
    public ResponseEntity<MessageReadResponseDto> readMessage(@RequestParam String id) {
        return ResponseEntity.ok().body(messageService.readMessage(id));
    }

    @GetMapping("/list")
    public ResponseEntity<MessageReadListResponseDto> readListMessage() {
        return ResponseEntity.ok().body(messageService.readListMessage());
    }
}
