package com.bookbook.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignUpUserDTO {
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String password;

    private String introduce = "";

    protected SignUpUserDTO() {}

    private SignUpUserDTO(String nickname, String password, String introduce) {
        this.nickname = nickname;
        this.password = password;
        this.introduce = introduce;
    }

    public static SignUpUserDTO createUserDTO(String nickname, String password, String introduce) {
        return new SignUpUserDTO(nickname, password, introduce);
    }
}
