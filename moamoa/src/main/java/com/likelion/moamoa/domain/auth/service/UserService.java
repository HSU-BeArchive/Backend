package com.likelion.moamoa.domain.auth.service;

import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserRes;

public interface UserService {
    // 회원 가입
    SignupUserRes signup(SignupUserReq signupUserReq);
}
