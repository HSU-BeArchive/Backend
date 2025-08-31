package com.likelion.moamoa.domain.user.service;

import com.likelion.moamoa.domain.user.entity.User;
import com.likelion.moamoa.domain.user.web.dto.*;

public interface UserService {
    // 이메일 중복 체크 확인
    User checkEmail(String email);

    // 회원 가입
    SignupUserRes signup(SignupUserReq signupUserReq);

    // 로그인
    SigninUserRes singin(SigninUserReq signinUserReq);
}
