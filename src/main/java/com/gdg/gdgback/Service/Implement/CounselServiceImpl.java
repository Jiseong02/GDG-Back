package com.gdg.gdgback.Service.Implement;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Counsel.CounselDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;
import com.gdg.gdgback.Document.CounselDocument;
import com.gdg.gdgback.Exception.CounselNotFoundException;
import com.gdg.gdgback.Exception.UserNotFoundException;
import com.gdg.gdgback.Repository.CounselRepository;
import com.gdg.gdgback.Repository.UserRepository;
import com.gdg.gdgback.Service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselServiceImpl implements CounselService {
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;

    @Autowired
    CounselServiceImpl(CounselRepository counselRepository, UserRepository userRepository) {
        this.counselRepository = counselRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createCounsel(CounselCreateRequestDto createRequestDto) throws IllegalArgumentException {
        if(!userRepository.existsById(createRequestDto.getUserId())) {
            throw new UserNotFoundException();
        }

        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();

        return counselRepository.save(counselDocument).getId();
    }

    @Override
    public CounselReadResponseDto readCounsel(String id) throws IllegalArgumentException {
        CounselDocument counselDocument = counselRepository.findById(id)
                .orElseThrow(CounselNotFoundException::new);

        return CounselReadResponseDto.builder()
                .id(counselDocument.getId())
                .userId(counselDocument.getUserId())
                .date(counselDocument.getDate())
                .seconds(counselDocument.getSeconds())
                .summation(counselDocument.getSummation())
                .build();
    }

    @Override
    public void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws IllegalArgumentException {
        CounselDocument counselDocument = counselRepository.findById(deleteRequestDto.getId())
                .orElseThrow(CounselNotFoundException::new);

        counselRepository.delete(counselDocument);
    }
}
