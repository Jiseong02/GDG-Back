package com.gdg.gdgback.Repository;

import com.gdg.gdgback.Document.DiaryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends MongoRepository<DiaryDocument, String> {
}
