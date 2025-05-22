package com.likelion.moamoa.domain.auth.service;

import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.exception.DuplicateLoginIdException;
import com.likelion.moamoa.domain.auth.exception.InvalidPasswordException;
import com.likelion.moamoa.domain.auth.exception.NotFoundLoginIdException;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import com.likelion.moamoa.domain.auth.web.dto.SigninUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SigninUserRes;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Override
    public SignupUserRes signup(SignupUserReq signupUserReq) {
        // 아이디 중복 검사
        if (userRepository.existsByLoginId(signupUserReq.getLoginId())) {
            // 아이디 중복이 있는 경우 에러 반환
            throw new DuplicateLoginIdException();
        }

        // User 생성
        User user = User.builder()
                .loginId(signupUserReq.getLoginId())
                .password(signupUserReq.getPassword())
                .build();

        // User 저장
        User saveUser = userRepository.save(user);

        // 반환
        return new SignupUserRes(saveUser.getUserId());
    }

    // 로그인
    @Override
    public SigninUserRes singin(SigninUserReq signinUserReq) {
        // 아이디 존재 여부 검사
        User user = userRepository.findByLoginId(signinUserReq.getLoginId())
                // 존재 X
                .orElseThrow(NotFoundLoginIdException::new);

        // 비밀번호 확인 검사
        if(!user.getPassword().equals(signinUserReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 반환
        return new SigninUserRes(user.getLoginId());
    }

}
