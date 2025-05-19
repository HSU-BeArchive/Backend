package com.likelion.moamoa.domain.auth.web.controller;

import com.likelion.moamoa.domain.auth.service.UserService;
import com.likelion.moamoa.domain.auth.web.dto.SigninUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SigninUserRes;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SuccessResponse<?>> signup(
            @RequestBody @Valid
            SignupUserReq signupUserReq
    ) {
        // 서비스
        SignupUserRes signupUserRes = userService.signup(signupUserReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(signupUserRes));
    }

    // 회원 로그인
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> signin(
            @RequestBody @Valid
            SigninUserReq signinUserReq
    ) {
        // 서비스
        SigninUserRes signinUserRes = userService.singin(signinUserReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(signinUserRes));
    }

}
