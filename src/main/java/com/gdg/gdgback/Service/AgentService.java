package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Chat;

import java.io.IOException;

public interface AgentService {
    String invoke(Chat chat) throws IOException;
}
