package com.gdg.gdgback.Message;

import com.gdg.gdgback.Message.DTO.MessageCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Async
    public void createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        MessageDocument messageDocument = MessageMapper.map(messageCreateRequestDto);
        messageRepository.save(messageDocument);
    }
}
