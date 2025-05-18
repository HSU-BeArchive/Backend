package com.likelion.moamoa.domain.auth.service;


import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.exception.DuplicateLoginIdException;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserReq;
import com.likelion.moamoa.domain.auth.web.dto.SignupUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

        // repository User 저장 (userRepository 사용)
        User savedUser = userRepository.save(user);

        // 반환
        return new SignupUserRes(savedUser.getUserId());
    }

}
