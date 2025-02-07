package com.gdg.gdgback.DTO;

import com.gdg.gdgback.Domain.User;
import lombok.Data;

@Data
public class UserCreationDto implements User {
    private String id;
    private String name;
}
