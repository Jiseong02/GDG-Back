package com.gdg.gdgback;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.CounselDocument;
import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselRepository;
import com.gdg.gdgback.Counsel.CounselServiceImpl;
import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {
    CounselRepository counselRepository = mock(CounselRepository.class);
    AgentService agentService = mock(AgentService.class);
    Validator validator = mock(Validator.class);

    @InjectMocks
    CounselServiceImpl counselService;

    @BeforeEach
    void setUp() {
        try {
            doReturn("testResponse").when(agentService).getTextResponse(any(AgentTextRequestDto.class));
        } catch(IOException e){
            System.out.println("IOException 발생\n");
        }
        doThrow(UserNotExistsException.class).when(validator).validateUserExists(anyString());
        doNothing().when(validator).validateUserExists("testId");
        doThrow(CounselNotExistsException.class).when(validator).validateCounselExists(anyString());
        doNothing().when(validator).validateCounselExists("testId");

        CounselDocument testDocument = CounselDocument.builder()
                .id("testId")
                .userId("testUserId")
                .startTime(LocalDateTime.now().minusMinutes(3L))
                .endTime(LocalDateTime.now())
                .summation("testSummation")
                .build();

        doReturn(Optional.of(testDocument)).when(counselRepository).save(any(CounselDocument.class));
        doReturn(Optional.empty()).when(counselRepository).findById(anyString());
        doReturn(Optional.of(testDocument)).when(counselRepository).findById("testId");
    }

    @Test
    void createCounsel() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("testId")
                .build();

        counselService.createCounsel(dto).getId();
    }
    @Test
    void createCounselNotExistUser() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("id_not_exists")
                .build();

        Assertions.assertThrows(UserNotExistsException.class, ()->counselService.createCounsel(dto));
    }
}
