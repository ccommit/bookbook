package com.bookbook.dto.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class PasswordUpdateRequest {

    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;

    private PasswordUpdateRequest() {}

    public PasswordUpdateRequest(String originPassword, String newPassword) {
        this.oldPassword = originPassword;
        this.newPassword = newPassword;
    }
}
