package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselDocument;
import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Global.Validator;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DiaryServiceTest {
    DiaryRepository diaryRepository = mock(DiaryRepository.class);
    CounselService counselService = mock(CounselService.class);

    @Spy
    Validator validator;

    @InjectMocks
    DiaryServiceImpl diaryService;

    @BeforeEach
    void setUp() {
        DiaryDocument testDocument = DiaryDocument.builder()
                .id(ID)
                .userId(USERID)
                .counselId(COUNSELID)
                .category(new String[3])
                .score(5)
                .date(LocalDateTime.now())
                .expected(true)
                .picture(new byte[3])
                .title("testTitle")
                .content("testContent")
                .build();

        doReturn(testDocument).when(diaryRepository).save(any(DiaryDocument.class));
        doReturn(Optional.empty()).when(diaryRepository).findById(anyString());
        doReturn(Optional.of(testDocument)).when(diaryRepository).findById("testId");
        doReturn(new ArrayList<CounselDocument>()).when(diaryRepository).findAll();
    }

    @Test
    void createDiary() {
        DiaryCreateRequestDto dto = DiaryCreateRequestDto.builder()
                .userId(USERID)
                .counselId(COUNSELID)
                .category(new String[3])
                .score(5)
                .expected(true)
                .picture(new byte[3])
                .title("testTitle")
                .content("testContent")
                .build();
        Assertions.assertDoesNotThrow(()->diaryService.createDiary(dto));
    }
    @Test
    void createDiaryWithNotExistingUser() {
        DiaryCreateRequestDto dto = DiaryCreateRequestDto.builder()
                .userId("I want to go home")
                .counselId(COUNSELID)
                .category(new String[3])
                .score(5)
                .expected(true)
                .picture(new byte[3])
                .title("testTitle")
                .content("testContent")
                .build();
        Assertions.assertThrows(UserNotExistsException.class, ()->diaryService.createDiary(dto));
    }
    @Test
    void createDiaryWithNotExistingCounsel() {
        DiaryCreateRequestDto dto = DiaryCreateRequestDto.builder()
                .userId("I want to go home")
                .counselId(COUNSELID)
                .category(new String[3])
                .score(5)
                .expected(true)
                .picture(new byte[3])
                .title("testTitle")
                .content("testContent")
                .build();
        Assertions.assertThrows(UserNotExistsException.class, ()->diaryService.createDiary(dto));
    }
}
