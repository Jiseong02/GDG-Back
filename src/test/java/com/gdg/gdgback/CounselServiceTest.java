package com.gdg.gdgback;

import com.gdg.gdgback.Agent.Service.AgentService;
import com.gdg.gdgback.Counsel.CounselRepository;
import com.gdg.gdgback.Counsel.CounselServiceImpl;
import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Global.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {
    CounselRepository counselRepository = mock(CounselRepository.class);
    AgentService agentService = mock(AgentService.class);
    Validator validator = mock(Validator.class);

    @InjectMocks
    CounselServiceImpl counselService;

    void setUp() {
        when(validator.validateUserExists())
    }

    @Test
    void createCounsel() {
        CounselCreateRequestDto dto = CounselCreateRequestDto.builder()
                .userId("test")
                .build();

        Assertions.assertDoesNotThrow(()->counselService.createCounsel(dto));
    }
}
