package com.gdg.gdgback.Diary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends MongoRepository<DiaryDocument, String> {
    List<DiaryDocument> findAllByUserIdOrderByDateDesc(String id);
}
