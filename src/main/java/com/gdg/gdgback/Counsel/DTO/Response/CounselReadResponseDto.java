package com.gdg.gdgback.Counsel.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CounselReadResponseDto {
    String id;
    String userId;

    Date startTime;
    Date endTime;
    long seconds;
    String summation;
}
