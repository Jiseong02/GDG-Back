package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiaryServiceTest {
    @Spy
    DiaryRepository diaryRepository = new DiaryTestRepository();
    CounselService counselService = mock(CounselService.class);

    @Spy
    Validator validator = new TestValidator();

    @InjectMocks
    DiaryServiceImpl diaryService;

    @BeforeEach
    void setUp() {
        CounselReadResponseDto counselReadResponseDto = CounselReadResponseDto.builder().build();
        doReturn(counselReadResponseDto).when(counselService).readCounsel("test");
    }

    @Test
    void createDiary() {
        DiaryCreateRequestDto dto = DiaryCreateRequestDto.builder()
                .userId("test")
                .counselId("test")
                .category(new String[3])
                .score(5)
                .expected(true)
                .picture(new byte[3])
                .title("test")
                .content("test")
                .build();
        Assertions.assertDoesNotThrow(()->diaryService.createDiary(dto));
    }
    @Test
    void createDiaryWithNotExistingUser() {
        DiaryCreateRequestDto dto = DiaryCreateRequestDto.builder()
                .userId("I want to go home")
                .counselId("test")
                .category(new String[3])
                .score(5)
                .expected(true)
                .picture(new byte[3])
                .title("test")
                .content("test")
                .build();
        Assertions.assertThrows(UserNotExistsException.class, ()->diaryService.createDiary(dto));
    }
}
