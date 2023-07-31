package com.bookbook.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SignUpRequest {
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;

    private String introduce = "";

    private SignUpRequest() {}

    public SignUpRequest(String userId, String password, String introduce) {
        this.userId = userId;
        this.password = password;
        this.introduce = introduce;
    }

    public SignUpRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public void updateHashedPassword(String hashedPassword) {
        this.password = hashedPassword;
    }
}
