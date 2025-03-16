package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;
import com.gdg.gdgback.User.Exception.UserAlreadyExistsException;
import com.gdg.gdgback.User.Exception.UserNotExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/list")
    public ResponseEntity<UserReadListResponseDto> readUserList() {
        return ResponseEntity.ok(userService.readUserList());
    }
    @GetMapping
    public ResponseEntity<UserReadResponseDto> readUser(String id) throws UserNotExistsException {
        return ResponseEntity.ok(userService.readUser(id));
    }
    @PostMapping
    public ResponseEntity<java.lang.String> createUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) throws UserAlreadyExistsException {
        userService.createUser(userCreateRequestDto);
        return ResponseEntity.ok("회원가입 되었습니다.");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDeleteRequestDto deleteRequestDto) throws UserNotExistsException {
        userService.deleteUser(deleteRequestDto);
        return ResponseEntity.ok("정상적으로 삭제되었습니다.");
    }
}
