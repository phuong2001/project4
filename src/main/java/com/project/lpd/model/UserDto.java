package com.project.lpd.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    private String fullName;
    private String password;
    private String matchingPassword;
    private String email;

    // standard getters and setters
}
