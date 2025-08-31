package com.likelion.moamoa.domain.user.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCheckReq {

    @Email(message = "유효한 이메일 주소가 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    private String email;
}
