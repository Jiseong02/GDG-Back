package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Document.MessageDocument;
import com.gdg.gdgback.Domain.Message;
import com.gdg.gdgback.Repository.MessageRepository;
import com.gdg.gdgback.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void saveMessage(Message message) {
    }
}
