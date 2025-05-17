package com.likelion.moamoa.domain.auth.web.controller;

import com.likelion.moamoa.domain.auth.service.UserService;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    // 의존성 부여
    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> signup(@RequestBody @Valid SignupUserReq signupUserReq) {
        userService.signup(signupUserReq);
        return ResponseEntity.ok(SuccessResponse.created(null));
    }
    // 회원 생성


}
