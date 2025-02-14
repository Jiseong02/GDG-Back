package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AgentChatRequestDto implements Chat {
    private String text;
}
