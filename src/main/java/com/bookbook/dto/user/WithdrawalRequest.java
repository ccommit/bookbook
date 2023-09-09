package com.bookbook.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WithdrawalRequest {

    @NotBlank
    private String password;

    private WithdrawalRequest() {}

    public WithdrawalRequest(String password) {
        this.password = password;
    }
}
