package com.gdg.gdgback.Diary.DTO.Request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class DiaryImageUploadRequestDto {
    String id;
    MultipartFile image;
}
