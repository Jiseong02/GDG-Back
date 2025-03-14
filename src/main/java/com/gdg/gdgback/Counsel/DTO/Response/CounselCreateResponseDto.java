package com.gdg.gdgback.Counsel.DTO.Response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CounselCreateResponseDto {
    String id;
    byte[] content;
}
