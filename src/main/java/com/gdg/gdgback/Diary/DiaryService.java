package com.gdg.gdgback.Diary;

import com.gdg.gdgback.Diary.DTO.Request.DiaryCreateRequestDto;
import com.gdg.gdgback.Diary.DTO.Request.DiaryDeleteRequestDto;
import com.gdg.gdgback.Diary.DTO.DiaryReadResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DiaryService{
    private final DiaryRepository diaryRepository;

    DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public String createDiary(DiaryCreateRequestDto createRequestDto) {
        return diaryRepository.save(
                DiaryDocument.builder()
                        .userId(createRequestDto.getUserId())
                        .counselId(createRequestDto.getCounselId())
                        .picture(createRequestDto.getPicture())
                        .category(createRequestDto.getCategory())
                        .score(createRequestDto.getScore())
                        .title(createRequestDto.getTitle())
                        .content(createRequestDto.getContent())
                        .build()
        ).getId();
    }

    public DiaryReadResponseDto readDiary(String id) {
        DiaryDocument diaryDocument = diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);

        return DiaryReadResponseDto.builder()
                .id(diaryDocument.getId())
                .userId(diaryDocument.getUserId())
                .counselId(diaryDocument.getCounselId())
                .date(diaryDocument.getDate())
                .picture(diaryDocument.getPicture())
                .category(diaryDocument.getCategory())
                .score(diaryDocument.getScore())
                .title(diaryDocument.getTitle())
                .content(diaryDocument.getContent())
                .build();
    }

    public void deleteDiary(DiaryDeleteRequestDto deleteRequestDto) throws DiaryNotFoundException {
        DiaryDocument diaryDocument = diaryRepository.findById(deleteRequestDto.getId())
                .orElseThrow(DiaryNotFoundException::new);

        diaryRepository.delete(diaryDocument);
    }
}
