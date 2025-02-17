package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.Diary.DiaryCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Diary.DiaryReadRequestDto;
import com.gdg.gdgback.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dairy")
public class DiaryController {
    DiaryService diaryService;

    @Autowired
    DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    ResponseEntity<String> createDiary(DiaryCreateRequestDto createRequestDto) {
        return ResponseEntity.ok().body(diaryService.createDiary(createRequestDto));
    }
    @GetMapping
    ResponseEntity<DiaryReadResponseDto> readDiary(DiaryReadRequestDto readRequestDto) {
        return ResponseEntity.ok().body(diaryService.readDiary(readRequestDto));
    }
}
