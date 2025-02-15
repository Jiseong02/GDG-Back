package com.gdg.gdgback.Document;

import com.gdg.gdgback.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@AllArgsConstructor
@Document(collection = "user")
public class UserDocument {
    @Id
    private String id;
    private String name;

    public static UserDocument of(User user) {
        return new UserDocumentBuilder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
