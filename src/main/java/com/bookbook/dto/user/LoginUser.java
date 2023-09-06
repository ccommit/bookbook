package com.bookbook.dto.user;

import lombok.Getter;

@Getter
public class LoginUser {
    private final Long id;
    private String hashedPassword;
    private final UserRole userRole;
    private String introduce;

    public LoginUser(UserInfo user) {
        this.id = user.getId();
        this.hashedPassword = user.getHashedPassword();
        this.userRole = UserRole.getByUpperCaseName(user.getRole());
        this.introduce = user.getIntroduce();
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }

    public void updateHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
