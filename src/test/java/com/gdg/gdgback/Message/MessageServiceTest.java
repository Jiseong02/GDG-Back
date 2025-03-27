package com.gdg.gdgback.Message;

import com.gdg.gdgback.General.TestValidator;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.Message.DTO.Request.MessageCreateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    MessageRepository messageRepository = new MessageTestRepository();
    Validator validator = new TestValidator();
    MessageService messageService = new MessageServiceImpl(messageRepository, validator);

    @Test
    void createMessage() {
        MessageCreateRequestDto dto = MessageCreateRequestDto.builder()
                .counselId("test")
                .role("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->messageService.createMessage(dto));
    }

}
