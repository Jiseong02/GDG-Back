package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    DiaryService diaryService;

    @Autowired
    DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    ResponseEntity<DiaryReadResponseDto> readDiary(@RequestParam String id) {
        return ResponseEntity.ok().body(diaryService.readDiary(id));
    }

    @GetMapping("/list")
    ResponseEntity<DiaryReadListResponseDto> readDiaryList() {
        return ResponseEntity.ok().body(diaryService.readDiaryList());
    }

    @PostMapping
    ResponseEntity<String> createDiary(DiaryCreateRequestDto createRequestDto) {
        return ResponseEntity.ok().body(diaryService.createDiary(createRequestDto));
    }

    @PostMapping("/delete")
    ResponseEntity<String> deleteDiary(DiaryDeleteRequestDto deleteRequestDto) {
        this.diaryService.deleteDiary(deleteRequestDto);
        return ResponseEntity.ok().body("정상적으로 일지가 삭제되었습니다.");
    }
}
