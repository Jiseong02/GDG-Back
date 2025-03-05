package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.Diary.DiaryCreateRequestDto;
import com.gdg.gdgback.DTO.Request.Diary.DiaryDeleteRequestDto;
import com.gdg.gdgback.DTO.Response.DiaryReadResponseDto;
import com.gdg.gdgback.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<DiaryReadResponseDto> readDiary(@RequestParam String id) {
        return ResponseEntity.ok().body(diaryService.readDiary(id));
    }
    @PostMapping("/delete")
    ResponseEntity<String> deleteDiary(DiaryDeleteRequestDto deleteRequestDto) {
        this.diaryService.deleteDiary(deleteRequestDto);
        return ResponseEntity.ok().body("정상적으로 일지가 삭제되었습니다.");
    }
}
