package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Response.CounselReadByUserIdResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadListResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CounselMapper {
    public static CounselReadResponseDto documentToDto(CounselDocument counselDocument) {
        LocalDateTime startTime = counselDocument.getStartTime();
        LocalDateTime endTime = counselDocument.getEndTime();
        if(endTime == null) {
            endTime = LocalDateTime.now();
        }

        return CounselReadResponseDto.builder()
                .id(counselDocument.getId())
                .userId(counselDocument.getUserId())
                .startTime(startTime)
                .endTime(endTime)
                .seconds(Duration.between(startTime, endTime).toSeconds())
                .summation(counselDocument.getSummation())
                .build();
    }

    public static CounselReadListResponseDto documentToReadListDto(List<CounselDocument> counselDocumentList) {
        ArrayList<CounselReadResponseDto> counselDtoList = new ArrayList<>();
        for(CounselDocument counselDocument : counselDocumentList) {
            counselDtoList.add(CounselMapper.documentToDto(counselDocument));
        }
        return CounselReadListResponseDto.builder()
                .counsels(counselDtoList)
                .build();
    }

    public static CounselReadByUserIdResponseDto documentToReadByUserIdDto(List<CounselDocument> counselDocumentList) {
        ArrayList<CounselReadResponseDto> counselDtoList = new ArrayList<>();
        for(CounselDocument counselDocument : counselDocumentList) {
            counselDtoList.add(CounselMapper.documentToDto(counselDocument));
        }
        return CounselReadByUserIdResponseDto.builder()
                .counsels(counselDtoList)
                .build();
    }
}
