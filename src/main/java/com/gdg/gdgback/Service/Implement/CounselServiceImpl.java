package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Counsel.CounselReadRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;
import com.gdg.gdgback.Document.CounselDocument;
import com.gdg.gdgback.Repository.CounselRepository;
import com.gdg.gdgback.Service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselServiceImpl implements CounselService {
    private final CounselRepository counselRepository;

    @Autowired
    CounselServiceImpl(CounselRepository counselRepository) {
        this.counselRepository = counselRepository;
    }

    @Override
    public String createCounsel(CounselCreateRequestDto createRequestDto) {
        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();
        return counselRepository.save(counselDocument).getId();
    }

    @Override
    public CounselReadResponseDto readCounsel(CounselReadRequestDto readRequestDto) {
        CounselDocument counselDocument = counselRepository.findById(readRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기록입니다."));

        return CounselReadResponseDto.builder()
                .id(counselDocument.getId())
                .userId(counselDocument.getUserId())
                .date(counselDocument.getDate())
                .seconds(counselDocument.getSeconds())
                .summation(counselDocument.getSummation())
                .build();
    }
}
