package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadByUserIdResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadListResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselService {
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;

    @Autowired
    CounselService(CounselRepository counselRepository, UserRepository userRepository) {
        this.counselRepository = counselRepository;
        this.userRepository = userRepository;
    }

    public String createCounsel(CounselCreateRequestDto createRequestDto) throws UserNotExistsException {
        if(!userRepository.existsById(createRequestDto.getUserId())) {
            throw new UserNotExistsException();
        }

        CounselDocument counselDocument = CounselDocument.builder()
                .userId(createRequestDto.getUserId())
                .build();

        return counselRepository.save(counselDocument).getId();
    }

    public CounselReadResponseDto readCounsel(String id) throws CounselNotExistsException {
        CounselDocument counselDocument = counselRepository.findById(id)
                .orElseThrow(CounselNotExistsException::new);

        return CounselMapper.documentToDto(counselDocument);
    }

    public CounselReadListResponseDto readCounselList() {
        return CounselMapper.documentToReadListDto(counselRepository.findAll());
    }

    public CounselReadByUserIdResponseDto readCounselByUserId(String id) throws UserNotExistsException {
        if(!userRepository.existsById(id)) {
            throw new UserNotExistsException();
        }

        return CounselMapper.documentToReadByUserIdDto(counselRepository.findAllByUserId(id));
    }

    public void deleteCounsel(CounselDeleteRequestDto deleteRequestDto) throws CounselNotExistsException {
        CounselDocument counselDocument = counselRepository.findById(deleteRequestDto.getId())
                .orElseThrow(CounselNotExistsException::new);

        counselRepository.delete(counselDocument);
    }
}
