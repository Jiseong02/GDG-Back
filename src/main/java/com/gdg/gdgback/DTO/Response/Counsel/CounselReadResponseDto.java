package com.gdg.gdgback.DTO.Response.Counsel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CounselReadResponseDto {
    String id;
    String userId;

    LocalDateTime date;
    int seconds;
    String summation;
}
