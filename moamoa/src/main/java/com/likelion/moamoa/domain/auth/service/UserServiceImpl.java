package com.likelion.moamoa.domain.auth.service;


import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.exception.DuplicateLoginIdException;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // 회원 가입
    @Override
    @Transactional
    public void signup(SignupUserReq signupUserReq) {
        // 아이디 중복 검사
        if (userRepository.existsByLoginId(signupUserReq.getLoginId())) {
            // 아이디 중복이 있는 경우 에러 반환
            throw new DuplicateLoginIdException();
        }
        // 회원 생성
        User user = new User();
        user.setLoginId(signupUserReq.getLoginId());
        // 비밀번호 암호화 저장
        user.setPassword(signupUserReq.getPassword());
        // 회원 저장
        userRepository.save(user);
    }





}
