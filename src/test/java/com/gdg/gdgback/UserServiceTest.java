package com.gdg.gdgback;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserDocument;
import com.gdg.gdgback.User.UserRepository;
import com.gdg.gdgback.User.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUserRepository() {
        // 모조 리포지토리에 "exist"라는 아이디를 가진 유저가 저장되어 있다고 가정한다.
        UserDocument mockedUser = UserDocument.builder()
                .id("exist")
                .name("옥찌")
                .build();

        doReturn(Optional.empty()).when(userRepository).findById(anyString());
        doReturn(Optional.of(mockedUser)).when(userRepository).findById("exist");
        doReturn(false).when(userRepository).existsById(anyString());
        doReturn(true).when(userRepository).existsById("exist");

        when(userRepository.existsById(anyString())).thenReturn(false);
        when(userRepository.existsById("exist")).thenReturn(true);
    }

    @Test
    void createUser() {
        UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
                .id("notExist")
                .name("빵빵이")
                .build();
        assertDoesNotThrow(() -> userService.createUser(userCreateRequestDto));
    }

    @Test
    void createExistingUser() {
        UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
                .id("exist")
                .name("옥찌")
                .build();
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userCreateRequestDto));
    }

    @Test
    void readUser() {
        String id = "exist";
        assertDoesNotThrow(() -> userService.readUser(id));
    }

    @Test
    void readNotExistingUser() {
        String id = "notExist";
        assertThrows(UserNotExistsException.class, () -> userService.readUser(id));
    }
}
