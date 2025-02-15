package com.gdg.gdgback.Repository;

import com.gdg.gdgback.Document.ChatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatDocument, String> {
}
