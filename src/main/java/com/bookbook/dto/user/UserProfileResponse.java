package com.bookbook.dto.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserProfileResponse {

    @NotEmpty
    private String userId;
    private String introduce;
    private String createdAt;

    private UserProfileResponse() {}

    public UserProfileResponse(String userId, String introduce, String createdAt) {
        this.userId = userId;
        this.introduce = introduce;
        this.createdAt = createdAt;
    }
}
