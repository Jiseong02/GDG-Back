package com.gdg.gdgback.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("파라미터 오류 발생: " + e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        return ResponseEntity.badRequest().body("입력 오류 발생: " + e.getMessage());
    }
}
