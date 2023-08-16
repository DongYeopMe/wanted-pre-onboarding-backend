package com.example.wantedpreonboardingbackend.auth.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
