package com.likelion.moamoa.domain.auth.repository;

import com.likelion.moamoa.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// User 엔티티 DB 연동, 회원 가입 중복 검사
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginId(String loginId);

}
