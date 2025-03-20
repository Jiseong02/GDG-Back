package com.gdg.gdgback;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.CounselDocument;
import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselRepository;
import com.gdg.gdgback.Counsel.CounselServiceImpl;
import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        doReturn("testResponse").when(agentService).getTextResponse(any(AgentTextRequestDto.class));
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

        doReturn(testDocument).when(counselRepository).save(any(CounselDocument.class));
        doReturn(Optional.empty()).when(counselRepository).findById(anyString());
        doReturn(Optional.of(testDocument)).when(counselRepository).findById("testId");
        doReturn(new ArrayList<CounselDocument>()).when(counselRepository).findAll();
    }

    @Test
    void createCounsel() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("testId")
                .build();

        Assertions.assertDoesNotThrow(()->counselService.createCounsel(dto));
    }
    @Test
    void createCounselNotExistUser() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("id_not_exists")
                .build();

        Assertions.assertThrows(UserNotExistsException.class, ()->counselService.createCounsel(dto));
    }
    @Test
    void readCounsel() {
        String id = "testId";
        Assertions.assertDoesNotThrow(()->counselService.readCounsel(id));
    }
    @Test
    void readCounselNotExists() {
        String id = "not_exist_id";
        Assertions.assertThrows(CounselNotExistsException.class, ()->counselService.readCounsel(id));
    }
    @Test
    void readCounselList() {
        Assertions.assertDoesNotThrow(()->counselService.readCounselList());
    }
    @Test
    void deleteCounsel() {
        CounselDeleteRequestDto dto = CounselDeleteRequestDto.builder()
                .id("testId")
                .build();
        Assertions.assertDoesNotThrow(()->counselService.deleteCounsel(dto));
    }
    @Test
    void deleteCounselNotExists() {
        CounselDeleteRequestDto dto = CounselDeleteRequestDto.builder()
                .id("not_exist_id")
                .build();
        Assertions.assertThrows(CounselNotExistsException.class, ()->counselService.deleteCounsel(dto));
    }
}
