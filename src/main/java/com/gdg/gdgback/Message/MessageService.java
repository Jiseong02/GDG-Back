package com.gdg.gdgback.Message;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselRepository;
import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadListResponseDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final CounselRepository counselRepository;

    @Autowired
    MessageService(MessageRepository messageRepository, CounselRepository counselRepository) {
        this.messageRepository = messageRepository;
        this.counselRepository = counselRepository;
    }

    @Async
    public void createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        MessageDocument messageDocument = MessageMapper.map(messageCreateRequestDto);

        messageRepository.save(messageDocument);
    }

    public MessageReadListResponseDto readMessageByCounselId(String id) throws CounselNotExistsException {
        if(!counselRepository.existsById(id)) throw new CounselNotExistsException();

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
