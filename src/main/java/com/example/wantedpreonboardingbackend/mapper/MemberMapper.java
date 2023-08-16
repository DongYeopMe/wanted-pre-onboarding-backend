package com.example.wantedpreonboardingbackend.mapper;

import com.example.wantedpreonboardingbackend.dto.MemberPostDto;
import com.example.wantedpreonboardingbackend.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member userPostDtoTomember(MemberPostDto memberPostDto){
        Member member = new Member();
        member.updateEmail(memberPostDto.getEmail());
        member.updatePassword(memberPostDto.getPassword());
        member.updateName(memberPostDto.getName());
        return member;
    }
}
