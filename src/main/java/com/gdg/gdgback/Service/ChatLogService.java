package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Chat;

public interface ChatLogService {
    void addChat(Chat chat);
    String CreateChatLog();
}
