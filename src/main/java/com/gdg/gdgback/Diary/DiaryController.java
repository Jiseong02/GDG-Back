package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Diary.DTO.Request.*;
import com.gdg.gdgback.Diary.DTO.Response.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/user")
    ResponseEntity<DiaryReadListResponseDto> readDiaryListByUserId(@RequestParam String id) {
        return ResponseEntity.ok().body(diaryService.readDiaryListByUserId(id));
    }

    @PostMapping
    ResponseEntity<String> createDiary(@Valid @ModelAttribute DiaryCreateRequestDto createRequestDto) {
        return ResponseEntity.ok().body(diaryService.createDiary(createRequestDto));
    }

    @PostMapping("/delete")
    ResponseEntity<String> deleteDiary(@Valid @RequestBody DiaryDeleteRequestDto deleteRequestDto) {
        this.diaryService.deleteDiary(deleteRequestDto);
        return ResponseEntity.ok().body("정상적으로 일지가 삭제되었습니다.");
    }

    @PostMapping("/image")
    ResponseEntity<String> uploadDiaryImage(@Valid @ModelAttribute DiaryImageUploadRequestDto imageUploadRequestDto) {
        String imageUrl = this.diaryService.uploadDiaryImage(imageUploadRequestDto);
        return ResponseEntity.ok().body(imageUrl);
    }
}
