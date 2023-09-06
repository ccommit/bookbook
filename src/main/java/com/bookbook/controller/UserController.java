package com.bookbook.controller;


import com.bookbook.annotation.AdminLoginRequired;
import com.bookbook.annotation.UserLoginRequired;
import com.bookbook.annotation.IfLogin;
import com.bookbook.dto.user.*;
import com.bookbook.service.SessionLoginService;
import com.bookbook.service.UserService;
import com.bookbook.util.response.CommonResponse;
import com.bookbook.util.response.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final SessionLoginService loginService;

    public UserController(UserService userService, SessionLoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping
    public CommonResponse<Long> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return ResponseUtil.success(200, signUpRequest.getId());
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        UserInfo userInfo = userService.checkLoginInfo(loginRequest);
        loginService.signIn(userInfo);
        return ResponseUtil.success(200, userInfo.getUserId());
    }

    @GetMapping("/logout")
    public CommonResponse<Object> logout() {
        loginService.logout();
        return ResponseUtil.success(200, null);
    }

    @UserLoginRequired
    @PatchMapping("/me")
    public CommonResponse<Long> deleteUserOfMine(
            @RequestBody WithdrawalRequest withdrawalRequest,
            @IfLogin LoginUser loginUser) {
        userService.deleteUserOfMine(loginUser, withdrawalRequest);
        loginService.logout();
        return ResponseUtil.success(200, loginUser.getId());
    }

    @AdminLoginRequired
    @PatchMapping("/{userId}")
    public CommonResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseUtil.success(200, userId);
    }

    @UserLoginRequired
    @GetMapping("/me")
    public CommonResponse<UserProfileResponse> findUserProfile(@IfLogin LoginUser loginUser) {
        return ResponseUtil.success(200, userService.findUserProfile(loginUser));
    }

    @UserLoginRequired
    @PatchMapping("/me/password")
    public CommonResponse<Long> updatePassword(@IfLogin LoginUser loginUser, @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        userService.updatePassword(loginUser, passwordUpdateRequest);
        return ResponseUtil.success(200, loginUser.getId());
    }

    @UserLoginRequired
    @PatchMapping("/me/introduce")
    public CommonResponse<Long> updateIntroduce(@IfLogin LoginUser loginUser, @RequestBody IntroduceUpdateRequest introduceUpdateRequest) {
        userService.updateIntroduce(loginUser, introduceUpdateRequest);
        return ResponseUtil.success(200, loginUser.getId());
    }
}
