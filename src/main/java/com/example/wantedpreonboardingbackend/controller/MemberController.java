package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.auth.dto.MemberLoginRequestDto;
import com.example.wantedpreonboardingbackend.auth.dto.TokenInfo;
import com.example.wantedpreonboardingbackend.dto.MemberPostDto;
import com.example.wantedpreonboardingbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupMember(@RequestBody @Valid MemberPostDto memberPostDto){
        String name = memberService.createUser(memberPostDto);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        TokenInfo tokenInfo = memberService.login(memberLoginRequestDto);
        return tokenInfo;
    }


}
