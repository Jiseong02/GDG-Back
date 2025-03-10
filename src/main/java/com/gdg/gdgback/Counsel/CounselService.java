package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadByUserIdResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotFoundException;
import com.gdg.gdgback.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounselService {
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;

    @Autowired
    CounselService(CounselRepository counselRepository, UserRepository userRepository) {
        this.counselRepository = counselRepository;
        this.userRepository = userRepository;
    }

    public String createCounsel(CounselCreateRequestDto createRequestDto) throws IllegalArgumentException {
        if(!userRepository.existsById(createRequestDto.getUserId())) {
            throw new UserNotFoundException();
        }

        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();

        return counselRepository.save(counselDocument).getId();
    }

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

    public CounselReadByUserIdResponseDto readCounselByUserId(String id) throws UserNotFoundException {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        ArrayList<CounselReadResponseDto> counsels = new ArrayList<>();
        List<CounselDocument> counselDocuments = counselRepository.findAllByUserId(id);
        for(CounselDocument counselDocument : counselDocuments) {
            CounselReadResponseDto counsel = CounselReadResponseDto.builder()
                    .id(counselDocument.getId())
                    .userId(counselDocument.getUserId())
                    .date(counselDocument.getDate())
                    .seconds(counselDocument.getSeconds())
                    .summation(counselDocument.getSummation())
                    .build();
            counsels.add(counsel);
        }

        return CounselReadByUserIdResponseDto.builder()
                .counsels(counsels)
                .build();
    }

    public void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws IllegalArgumentException {
        CounselDocument counselDocument = counselRepository.findById(deleteRequestDto.getId())
                .orElseThrow(CounselNotFoundException::new);

        counselRepository.delete(counselDocument);
    }
}
