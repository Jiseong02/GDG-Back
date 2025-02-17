package com.gdg.gdgback.Repository;

import com.gdg.gdgback.Document.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageDocument, String> {
}
