package com.likelion.moamoa.domain.auth.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignupUserReq {
    @NotBlank(message = "아이디는 필수 입력 값 입니다.")
    @Size(min = 4, max = 20, message = "아이디는 영어+숫자 조합으로 4자 이상이어야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Size(min = 8, message = "비밀번호는 영문+숫자 조합으로 8자 이상이어야 합니다.")
    private String password;

}
