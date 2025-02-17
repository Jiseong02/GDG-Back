package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.User.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Request.User.UserReadRequestDto;
import com.gdg.gdgback.DTO.Response.User.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.User.UserReadResponseDto;
import com.gdg.gdgback.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<UserReadListResponseDto> getUserList() {
        return ResponseEntity.ok(userService.readUserList());
    }
    @GetMapping
    public ResponseEntity<UserReadResponseDto> readUser(UserReadRequestDto readRequestDto) throws IllegalArgumentException {
        return ResponseEntity.ok(userService.readUser(readRequestDto));
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException {
        userService.createUser(userCreateRequestDto);
        return ResponseEntity.ok("회원가입 되었습니다.");
    }
}
