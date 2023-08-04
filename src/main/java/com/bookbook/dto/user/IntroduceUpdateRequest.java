package com.bookbook.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class IntroduceUpdateRequest {

    @NotBlank
    private String introduce;

    private IntroduceUpdateRequest() {}

    public IntroduceUpdateRequest(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return introduce;
    }
}
