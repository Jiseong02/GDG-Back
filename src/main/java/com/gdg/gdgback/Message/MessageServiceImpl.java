package com.gdg.gdgback.Message;

import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.Message.DTO.Request.*;
import com.gdg.gdgback.Message.DTO.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("!test")
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    private final Validator validator;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository, Validator validator) {
        this.messageRepository = messageRepository;
        this.validator = validator;
    }

    @Async
    @Override
    public void createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        MessageDocument messageDocument = MessageMapper.map(messageCreateRequestDto);

        messageRepository.save(messageDocument);
    }

    @Override
    public MessageReadListResponseDto readMessageByCounselId(String id) {
        validator.validateCounselExists(id);

        List<MessageDocument> messages = messageRepository.findAllByCounselId(id);

        return MessageMapper.map(messages);
    }

    @Override
    public MessageReadResponseDto readMessage(String id) {
        MessageDocument messageDocument = messageRepository.findById(id)
                .orElseThrow(MessageNotExistsException::new);

        return MessageMapper.map(messageDocument);
    }

    @Override
    public MessageReadListResponseDto readListMessage() {
        List<MessageDocument> messages = messageRepository.findAll();

        return MessageMapper.map(messages);
    }
}
