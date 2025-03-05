package com.gdg.gdgback.DTO.Response.Counsel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CounselReadByUserIdResponseDto {
    List<CounselReadResponseDto> counsels;
}
