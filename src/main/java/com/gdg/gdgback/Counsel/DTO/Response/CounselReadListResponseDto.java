package com.gdg.gdgback.Counsel.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Builder
@Data
public class CounselReadListResponseDto {
    ArrayList<CounselReadResponseDto> counsels;
}
