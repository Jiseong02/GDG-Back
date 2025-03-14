package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Counsel.*;
import com.gdg.gdgback.Counsel.DTO.Response.CounselReadResponseDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadListResponseDto;
import com.gdg.gdgback.Diary.DTO.Response.DiaryReadResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService{
    private final CounselRepository counselRepository;
    private final DiaryRepository diaryRepository;

    DiaryService(CounselRepository counselRepository, DiaryRepository diaryRepository) {
        this.counselRepository = counselRepository;
        this.diaryRepository = diaryRepository;
    }

    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        return diaryRepository.save(DiaryMapper.dtoToDocument(createRequestDto)).getId();
    }

    public DiaryReadResponseDto readDiary(String id) {
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);
        return convertDocumentToDto(diaryDocument);
    }

    public DiaryReadListResponseDto readDiaryList() {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAll();
        return convertDocumentListToListDto(diaryDocumentList);
    }

    public DiaryReadListResponseDto readDiaryListByUserId(String id) {
        List<DiaryDocument> diaryDocumentList = diaryRepository.findAllByUserId(id);
        return convertDocumentListToListDto(diaryDocumentList);
    }

    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(DiaryNotFoundException::new);
        diaryRepository.delete(diaryDocument);
    }

    private DiaryReadResponseDto convertDocumentToDto(DiaryDocument document) {
        CounselReadResponseDto counsel = counselRepository.findById(document.getCounselId())
                .map(CounselMapper::documentToDto)
                .orElse(null);
        return DiaryMapper.documentToReadResponseDto(document, counsel);
    }

    private DiaryReadListResponseDto convertDocumentListToListDto(List<DiaryDocument> documentList) {
        ArrayList<DiaryReadResponseDto> dtoArrayList = new ArrayList<>();
        for(DiaryDocument document : documentList) {
            DiaryReadResponseDto dto = convertDocumentToDto(document);
            dtoArrayList.add(dto);
        }
        return DiaryReadListResponseDto.builder().diaries(dtoArrayList).build();
    }
}
