package com.gdg.gdgback.User;

import com.gdg.gdgback.User.DTO.Request.UserCreateRequestDto;
import com.gdg.gdgback.User.DTO.Request.UserDeleteRequestDto;
import com.gdg.gdgback.User.DTO.Response.UserReadListResponseDto;
import com.gdg.gdgback.User.DTO.Response.UserReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<UserReadListResponseDto> readUserList() {
        return ResponseEntity.ok(userService.readUserList());
    }
    @GetMapping
    public ResponseEntity<UserReadResponseDto> readUser(String id) throws IllegalArgumentException {
        return ResponseEntity.ok(userService.readUser(id));
    }
    @PostMapping
    public ResponseEntity<java.lang.String> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) throws IllegalArgumentException {
        userService.createUser(userCreateRequestDto);
        return ResponseEntity.ok("회원가입 되었습니다.");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDeleteRequestDto deleteRequestDto) throws IllegalArgumentException {
        userService.deleteUser(deleteRequestDto);
        return ResponseEntity.ok("정상적으로 삭제되었습니다.");
    }
}
