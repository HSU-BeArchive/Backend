package com.likelion.moamoa.domain.auth.web.controller;

import com.likelion.moamoa.domain.auth.service.UserService;
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
//    @PostMapping("/signin")
//    public ResponseEntity<SuccessResponse<?>> signin(@RequestBody @Valid SignupUserReq signupUserReq) {
//        userService.signin(signinUserReq);
//        return ResponseEntity.ok(SuccessResponse.ok(null));
//    }


}
