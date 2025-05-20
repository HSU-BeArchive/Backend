package com.likelion.moamoa.domain.auth.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignupUserReq {
    @NotBlank(message = "아이디는 필수 입력 값 입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4 ~ 20자이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "아이디는 영문자와 숫자를 반드시 포함해야 합니다")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20자이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "비밀번호는 영문자와 숫자를 반드시 포함해야 합니다")
    private String password;
}
