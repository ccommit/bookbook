package com.bookbook.dto;

import com.bookbook.util.password.BcryptEncoder;
import com.bookbook.util.password.Encoder;
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

    private final Encoder encoder = new BcryptEncoder();

    private SignUpRequest() {}

    public SignUpRequest(String userId, String password, String introduce) {
        this.userId = userId;
        this.introduce = introduce;
        encryptPassword(password);
    }

    public SignUpRequest(String userId, String password) {
        this.userId = userId;
        encryptPassword(password);
    }

    public void encryptPassword(String password) {
        this.password = encoder.hashPassword(password);
    }
}
