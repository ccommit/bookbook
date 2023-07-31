package com.bookbook.controller;


import com.bookbook.dto.SignUpRequest;
import com.bookbook.service.UserService;
import com.bookbook.util.response.CommonResponse;
import com.bookbook.util.response.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CommonResponse<Long> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return ResponseUtil.success(200, signUpRequest.getId());
    }
}
