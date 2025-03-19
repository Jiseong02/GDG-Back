package com.gdg.gdgback.Counsel;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Builder
@Getter
@Document("counsel")
public class CounselDocument {
    @Id
    String id;
    @Indexed
    String userId;
    @Indexed
    ZonedDateTime startTime;
    @Indexed
    ZonedDateTime endTime;
    String summation;
}
