package com.gdg.gdgback.Controller;

import com.gdg.gdgback.DTO.Request.CounselCreateRequestDto;
import com.gdg.gdgback.Service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok().body(counselService.generateCounsel(createRequestDto));
    }
}
