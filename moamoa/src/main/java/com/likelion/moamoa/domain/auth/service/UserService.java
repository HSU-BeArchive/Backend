package com.likelion.moamoa.domain.auth.service;

import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;

public interface UserService {
    //회원 가입 ( 생성만할 뿐 별도의 데이터를 반환할 필요가 없기 때문에 void 사용)
    void signup(SignupUserReq signupUserReq);
}
