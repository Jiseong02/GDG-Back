package com.gdg.gdgback.Message.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class MessageReadListResponseDto {
    ArrayList<MessageReadResponseDto> messages;
}
