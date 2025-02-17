package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.DTO.Response.UserReadResponseDto;
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
        return ResponseEntity.ok(userService.getUserList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserReadResponseDto> getUserById(@PathVariable String id) throws IllegalArgumentException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException {
        userService.addUser(userCreateRequestDto);
        return ResponseEntity.ok("회원가입 되었습니다.");
    }
}
