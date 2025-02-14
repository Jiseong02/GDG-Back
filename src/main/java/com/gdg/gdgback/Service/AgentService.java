package com.gdg.gdgback.Service;

import com.gdg.gdgback.Domain.Chat;
import com.google.protobuf.ByteString;

import java.io.IOException;

public interface AgentService {
    String getTextReply(Chat chat) throws IOException;
    ByteString getVoiceReply(Chat chat) throws IOException;
}
