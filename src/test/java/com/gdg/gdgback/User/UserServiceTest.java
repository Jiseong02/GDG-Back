package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Spy
    UserRepository userRepository = new UserTestRepository();

    @InjectMocks
    UserServiceImpl userService;

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
                .id("test")
                .name("버그 터지지 마라 제발")
                .build();
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(userCreateRequestDto));
    }

    @Test
    void readUser() {
        assertDoesNotThrow(() -> userService.readUser("test"));
    }

    @Test
    void readNotExistingUser() {
        String id = "id_not_exist";
        assertThrows(UserNotExistsException.class, () -> userService.readUser(id));
    }
    @Test
    void deleteUser() {
        UserDeleteRequestDto dto = UserDeleteRequestDto.builder()
                .id("test")
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
