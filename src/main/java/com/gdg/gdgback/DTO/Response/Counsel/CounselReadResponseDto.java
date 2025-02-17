package com.gdg.gdgback.DTO.Response.Counsel;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CounselReadResponseDto {
    String id;
    String userId;

    Date date;
    int seconds;
    String summation;
}
