package com.gdg.gdgback.Message;

import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadListResponseDto;
import com.gdg.gdgback.Message.DTO.Response.MessageReadResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    void createMessage(MessageCreateRequestDto messageCreateRequestDto);
    MessageReadListResponseDto readMessageByCounselId(String id);
    MessageReadResponseDto readMessage(String id);
    MessageReadListResponseDto readListMessage();
}
