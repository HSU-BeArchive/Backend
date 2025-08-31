package com.likelion.moamoa.domain.user.service;

import com.likelion.moamoa.domain.user.entity.User;
import com.likelion.moamoa.global.response.code.user.DuplicateEmailException;
import com.likelion.moamoa.global.response.code.user.InvalidPasswordException;
import com.likelion.moamoa.domain.user.repository.UserRepository;
import com.likelion.moamoa.domain.user.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 이메일 체크
    @Override
    public User checkEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(DuplicateEmailException::new);
    }

    // 회원 가입
    @Override
    @Transactional
    public SignupUserRes signup(SignupUserReq signupUserReq) {

        // User 생성
        User user = User.builder()
                .email(signupUserReq.getEmail())
                .password(signupUserReq.getPassword())
                .build();

        // User 저장
        User saveUser = userRepository.save(user);

        // 반환
        return new SignupUserRes(saveUser.getId());
    }

    // 로그인
    @Override
    public SigninUserRes singin(SigninUserReq signinUserReq) {
        User user = checkEmail(signinUserReq.getEmail());

        // 비밀번호 확인 검사
        if(!user.getPassword().equals(signinUserReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 반환
        return new SigninUserRes(
                user.getId(),
                user.getEmail());
    }
}
