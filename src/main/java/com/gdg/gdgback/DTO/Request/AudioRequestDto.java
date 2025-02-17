package com.gdg.gdgback.DTO.Request;

import lombok.*;

@Data
@Builder
public class AudioRequestDto {
    private String counselId;
    private byte[] content;
}
