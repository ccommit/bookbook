package com.bookbook.dto.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserInfo {

    private final Long id;
    @NotEmpty
    private final String userId;
    @NotEmpty
    private final String hashedPassword;
    private final String role;
    private final String introduce;

    private UserInfo(Long id, String userId, String hashedPassword, String role, String introduce) {
        this.id = id;
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.role = role;
        this.introduce = introduce;
    }
}
