package com.gdg.gdgback.Agent.DTO.Request;

import lombok.*;

@Data
@Builder
public class TextRequestDto {
    private String counselId;
    private String content;
}
