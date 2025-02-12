package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreationDto implements User {
    private String id;
    private String name;
}
