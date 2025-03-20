package com.gdg.gdgback;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import com.gdg.gdgback.User.UserDocument;
import com.gdg.gdgback.User.UserRepository;
import com.gdg.gdgback.User.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.gdg.gdgback.GdgbackApplicationTests.ID;
import static com.gdg.gdgback.GdgbackApplicationTests.NAME;

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
        UserDocument testUser = UserDocument.builder()
                .id(ID)
                .name(NAME)
                .build();

        doReturn(Optional.empty()).when(userRepository).findById(anyString());
        doReturn(Optional.of(testUser)).when(userRepository).findById(ID);
        doReturn(false).when(userRepository).existsById(anyString());
        doReturn(true).when(userRepository).existsById(ID);
        doReturn(false).when(userRepository).existsById(anyString());
        doReturn(true).when(userRepository).existsById(ID);
    }

    @Test
    void createUser() {
        UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
                .id("id_not_exist")
                .name("빵빵이")
                .build();
        assertDoesNotThrow(() -> userService.createUser(userCreateRequestDto));
    }

    @Test
    void createExistingUser() {
        UserCreateRequestDto userCreateRequestDto = UserCreateRequestDto.builder()
                .id(ID)
                .name("버그 터지지 마라 제발")
                .build();
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userCreateRequestDto));
    }

    @Test
    void readUser() {
        assertDoesNotThrow(() -> userService.readUser(ID));
    }

    @Test
    void readNotExistingUser() {
        String id = "id_not_exist";
        assertThrows(UserNotExistsException.class, () -> userService.readUser(id));
    }
    @Test
    void deleteUser() {
        UserDeleteRequestDto dto = UserDeleteRequestDto.builder()
                .id(ID)
                .build();
        assertDoesNotThrow(()->userService.deleteUser(dto));
    }
    @Test
    void deleteUserNotExists() {
        UserDeleteRequestDto dto = UserDeleteRequestDto.builder()
                .id("not_exist_id")
                .build();
        Assertions.assertThrows(UserNotExistsException.class, ()->userService.deleteUser(dto));
    }
}
