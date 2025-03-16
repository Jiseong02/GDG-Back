package com.gdg.gdgback.Message.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MessageReadResponseDto {
    String id;
    String counselId;
    String role;
    String content;
    LocalDateTime date;
}
