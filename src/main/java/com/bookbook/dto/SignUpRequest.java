package com.bookbook.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignUpRequest {
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;

    private String introduce = "";

    protected SignUpRequest() {}

    private SignUpRequest(String userId, String password, String introduce) {
        this.userId = userId;
        this.password = password;
        this.introduce = introduce;
    }

    public static SignUpRequest createSignUpRequest(String userId, String password, String introduce) {
        return new SignUpRequest(userId, password, introduce);
    }
}
