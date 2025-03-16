package com.gdg.gdgback.Agent.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class AgentTextRequestDto {
    private String counselId;

    @NotNull(message = "content는 필수 입력 값입니다.")
    private String content;
}
