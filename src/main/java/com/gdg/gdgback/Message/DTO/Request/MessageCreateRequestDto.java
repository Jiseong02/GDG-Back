package com.gdg.gdgback.Message.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageCreateRequestDto {
    String counselId;
    String role;
    String content;
}
