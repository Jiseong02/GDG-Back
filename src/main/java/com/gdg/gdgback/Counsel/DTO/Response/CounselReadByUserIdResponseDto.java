package com.gdg.gdgback.Counsel.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CounselReadByUserIdResponseDto {
    List<CounselReadResponseDto> counsels;
}
