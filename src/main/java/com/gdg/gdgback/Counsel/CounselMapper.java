package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadListResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CounselMapper {
    public static CounselReadResponseDto map(CounselDocument counselDocument) {
        ZonedDateTime startTime = counselDocument.getStartTime();
        ZonedDateTime endTime = counselDocument.getEndTime();
        if(endTime == null) {
            endTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        }

        return CounselReadResponseDto.builder()
                .id(counselDocument.getId())
                .userId(counselDocument.getUserId())
                .startTime(counselDocument.getStartTime())
                .endTime(endTime)
                .seconds(Duration.between(startTime.toInstant(), endTime.toInstant()).toSeconds())
                .summation(counselDocument.getSummation())
                .build();
    }

    public static CounselReadListResponseDto map(List<CounselDocument> counselDocumentList) {
        ArrayList<CounselReadResponseDto> counsels = counselDocumentList.stream()
                .map(CounselMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));

        return CounselReadListResponseDto.builder()
                .counsels(counsels)
                .build();
    }

    public static CounselDocument map(CounselCreateRequestDto dto) {
        return CounselDocument.builder()
                .userId(dto.getUserId())
                .startTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }
}
