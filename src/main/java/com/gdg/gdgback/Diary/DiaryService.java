package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.CounselNotExistsException;
import com.gdg.gdgback.Counsel.CounselService;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DiaryService{
    private final CounselService counselService;
    private final DiaryRepository diaryRepository;

    DiaryService(CounselService counselService, DiaryRepository diaryRepository) {
        this.counselService = counselService;
        this.diaryRepository = diaryRepository;
    }

    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        return diaryRepository.save(DiaryMapper.dtoToDocument(createRequestDto)).getId();
    }

    public DiaryReadResponseDto readDiary(String id) {
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);

        CounselReadResponseDto counsel = readCounselOrNull(diaryDocument);
        return DiaryMapper.documentToReadResponseDto(diaryDocument, counsel);
    }

    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(DiaryNotFoundException::new);

        diaryRepository.delete(diaryDocument);
    }

    public DiaryReadListResponseDto readDiaryList() {
        ArrayList<DiaryReadResponseDto> diaryList = new ArrayList<>();

        for(DiaryDocument diaryDocument : diaryRepository.findAll()) {
            CounselReadResponseDto counsel = readCounselOrNull(diaryDocument);
            DiaryReadResponseDto diary= DiaryMapper.documentToReadResponseDto(diaryDocument, counsel);

            diaryList.add(diary);
        }

        return DiaryReadListResponseDto.builder().diaries(diaryList).build();
    }

    private CounselReadResponseDto readCounselOrNull(DiaryDocument diaryDocument) {
        try {
            return counselService.readCounsel(diaryDocument.getCounselId());
        } catch (CounselNotExistsException e) {
            return null;
        }
    }
}
