package com.likelion.moamoa.domain.user.web.controller;

import com.likelion.moamoa.domain.user.service.UserService;
import com.likelion.moamoa.domain.user.web.dto.*;
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

    private final UserService userService;

    // 로그인 아이디 중복 체크 확인
    @PostMapping("check")
    public ResponseEntity<SuccessResponse<?>> check(
            @RequestBody @Valid EmailCheckReq emailCheckReq
    ) {
        userService.checkEmail(emailCheckReq.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<?>> signup(
            @RequestBody @Valid SignupUserReq signupUserReq
    ) {
        SignupUserRes signupUserRes = userService.signup(signupUserReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(signupUserRes));
    }

    // 회원 로그인
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<?>> signin(
            @RequestBody @Valid SigninUserReq signinUserReq
    ) {
        SigninUserRes signinUserRes = userService.singin(signinUserReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(signinUserRes));
    }

}
