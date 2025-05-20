package com.likelion.moamoa.domain.auth.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SigninUserReq {

    @NotBlank(message = "아이디는 필수 입력 값 입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    private String password;
    
}
