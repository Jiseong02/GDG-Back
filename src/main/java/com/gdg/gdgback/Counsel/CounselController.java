package com.gdg.gdgback.Counsel;

import com.gdg.gdgback.Counsel.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselDeleteRequestDto;
import com.gdg.gdgback.Counsel.DTO.Request.CounselEndRequestDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadByUserIdResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadListResponseDto;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counsel")
public class CounselController {
    CounselService counselService;

    @Autowired
    CounselController(CounselService counselService) {
        this.counselService = counselService;
    }

    @GetMapping
    ResponseEntity<CounselReadResponseDto> readCounsel(@RequestParam String id) {
        return ResponseEntity.ok().body(counselService.readCounsel(id));
    }

    @GetMapping("/list")
    ResponseEntity<CounselReadListResponseDto> readCounselList() {
        return ResponseEntity.ok().body(counselService.readCounselList());
    }

    @GetMapping("/user")
    ResponseEntity<CounselReadByUserIdResponseDto> readCounselByUserId(@RequestParam String id) {
        return ResponseEntity.ok().body(counselService.readCounselByUserId(id));
    }

    @PostMapping
    ResponseEntity<String> createCounsel(@Valid @RequestBody CounselCreateRequestDto createRequestDto) {
        return ResponseEntity.ok().body(counselService.createCounsel(createRequestDto));
    }

    @PostMapping("/end")
    ResponseEntity<String> endCounsel(@Valid @RequestBody CounselEndRequestDto counselEndRequestDto) {
        counselService.endCounsel(counselEndRequestDto);
        return ResponseEntity.ok().body("정상적으로 상담이 종료되었습니다.");
    }

    @PostMapping("/delete")
    ResponseEntity<String> deleteCounsel(@Valid @RequestBody CounselDeleteRequestDto deleteRequestDto) {
        counselService.deleteCounsel(deleteRequestDto);
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }
}
