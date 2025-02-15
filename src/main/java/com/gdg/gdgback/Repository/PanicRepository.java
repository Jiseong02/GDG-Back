package com.gdg.gdgback.Repository;

import com.gdg.gdgback.Document.PanicDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanicRepository extends MongoRepository<PanicDocument, String> {
}
