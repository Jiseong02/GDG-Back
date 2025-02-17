package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.Counsel.CounselCreateRequestDto;
import com.gdg.gdgback.DTO.Response.Counsel.CounselReadResponseDto;
import com.gdg.gdgback.Service.CounselService;
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

    @PostMapping
    ResponseEntity<String> createCounsel(@RequestBody CounselCreateRequestDto createRequestDto) {
        return ResponseEntity.ok().body(counselService.createCounsel(createRequestDto));
    }
    @GetMapping
    ResponseEntity<CounselReadResponseDto> readCounsel(@RequestParam String id) {
        return ResponseEntity.ok().body(counselService.readCounsel(id));
    }
}
