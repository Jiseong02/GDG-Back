package com.gdg.gdgback.Counsel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselRepository extends MongoRepository<CounselDocument, String> {
    List<CounselDocument> findAllByUserId(String id);
}
