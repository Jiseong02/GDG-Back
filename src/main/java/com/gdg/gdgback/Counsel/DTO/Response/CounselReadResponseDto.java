package com.gdg.gdgback.Counsel.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class CounselReadResponseDto {
    String id;
    String userId;

    ZonedDateTime startTime;
    ZonedDateTime endTime;
    long seconds;
    String summation;
}
