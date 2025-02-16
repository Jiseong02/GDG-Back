package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.AudioMessage;
import lombok.*;

@Getter
@Builder
public class AudioMessageDto implements AudioMessage {
    private String id;
    private String counselId;
    private String role;
    private byte[] content;
}
