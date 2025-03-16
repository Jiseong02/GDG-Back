package com.gdg.gdgback.Message;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Message.DTO.Request.*;
import com.gdg.gdgback.Message.DTO.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("!test")
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final CounselService counselService;

    @Autowired
    MessageService(MessageRepository messageRepository, CounselService counselService) {
        this.messageRepository = messageRepository;
        this.counselService = counselService;
    }

    @Async
    public void createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        MessageDocument messageDocument = MessageMapper.map(messageCreateRequestDto);

        messageRepository.save(messageDocument);
    }

    public MessageReadListResponseDto readMessageByCounselId(String id) throws CounselNotExistsException {
        counselService.validateCounselExists(id);

        List<MessageDocument> messages = messageRepository.findAllByCounselId(id);

        return MessageMapper.map(messages);
    }

    public MessageReadResponseDto readMessage(String id) throws MessageNotExistsException{
        MessageDocument messageDocument = messageRepository.findById(id)
                .orElseThrow(MessageNotExistsException::new);

        return MessageMapper.map(messageDocument);
    }

    public MessageReadListResponseDto readListMessage() {
        List<MessageDocument> messages = messageRepository.findAll();

        return MessageMapper.map(messages);
    }


}
