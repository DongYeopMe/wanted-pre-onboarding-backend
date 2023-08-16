package com.example.wantedpreonboardingbackend.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class MemberPostDto {
    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @Size(min = 8, message = "비밀번호는 8자 이상 입력해주세요.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String name;
}
