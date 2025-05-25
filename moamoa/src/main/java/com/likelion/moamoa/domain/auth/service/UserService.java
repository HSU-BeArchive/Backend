package com.likelion.moamoa.domain.auth.service;

import com.likelion.moamoa.domain.auth.web.dto.*;

public interface UserService {
    // 로그인 아이디 중복 체크 확인
    void loginIdCheck(loginIdCheckReq loginIdCheckReq);

    // 회원 가입
    SignupUserRes signup(SignupUserReq signupUserReq);

    // 로그인
    SigninUserRes singin(SigninUserReq signinUserReq);
}
