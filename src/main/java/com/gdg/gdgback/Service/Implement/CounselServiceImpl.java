package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.CounselCreateRequestDto;
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
    public String generateCounsel(CounselCreateRequestDto createRequestDto) {
        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();
        return counselRepository.save(counselDocument).getId();
    }
}
