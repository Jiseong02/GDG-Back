package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Agent.DTO.Request.AgentTextRequestDto;
import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.General.TestValidator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {
    @Spy
    CounselRepository counselRepository = new CounselTestRepository();
    AgentService agentService = mock(AgentService.class);
    @Spy
    Validator validator = new TestValidator();

    @InjectMocks
    CounselServiceImpl counselService;

    @BeforeEach
    void setUp() {
        doReturn("testResponse").when(agentService).getTextResponse(any(AgentTextRequestDto.class));
    }

    @Test
    void createCounsel() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("test")
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
        Assertions.assertDoesNotThrow(()->counselService.readCounsel("test"));
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
                .id("test")
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
