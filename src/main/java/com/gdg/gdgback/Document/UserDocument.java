package com.gdg.gdgback.Document;

import com.gdg.gdgback.Domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "user")
public class UserDocument {
    @Id
    private String id;
    private String name;

    static public UserDocument of(User user) {
        return new UserDocument(user.getId(), user.getName());
    }
}
