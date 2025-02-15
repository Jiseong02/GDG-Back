package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.Prompt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PromptDto implements Prompt {
    private String content;
}
