package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.Domain.Chat;
import com.gdg.gdgback.Repository.ChatRepository;
import com.gdg.gdgback.Service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatLogServiceImpl implements ChatLogService {
    ChatRepository chatRepository;

    @Autowired
    ChatLogServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void addChat(Chat chat) {
    }

    @Override
    public String CreateChatLog() {
        return "";
    }

}
