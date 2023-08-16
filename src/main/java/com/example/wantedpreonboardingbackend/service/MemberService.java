package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.auth.JwtTokenProvider;
import com.example.wantedpreonboardingbackend.auth.dto.MemberLoginRequestDto;
import com.example.wantedpreonboardingbackend.auth.dto.TokenInfo;
import com.example.wantedpreonboardingbackend.auth.util.SecurityUtil;
import com.example.wantedpreonboardingbackend.dto.MemberPostDto;
import com.example.wantedpreonboardingbackend.entity.Member;
import com.example.wantedpreonboardingbackend.mapper.MemberMapper;
import com.example.wantedpreonboardingbackend.repository.MemberRepository;
import com.example.wantedpreonboardingbackend.utils.exception.BusinessLogicException;
import com.example.wantedpreonboardingbackend.utils.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public String createUser(MemberPostDto memberPostDto){
        if(verifyExistEmail(memberPostDto)){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }
        if(verifyExistName(memberPostDto)){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
        }

        Member member = memberMapper.userPostDtoTomember(memberPostDto);
        memberRepository.save(member);

        return member.getName();
    }
    @Transactional
    public TokenInfo login(MemberLoginRequestDto memberLoginRequestDto){
        Optional<Member> findMember = memberRepository.findByEmail(memberLoginRequestDto.getEmail());
        findMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.Member_NOT_FOUND));
        Member member = findMember.get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (member.getId(), memberLoginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    private boolean verifyExistEmail(MemberPostDto memberPostDto) {
        Optional<Member> user = memberRepository.findByEmail(memberPostDto.getEmail());
        return user.isPresent();
    }
    private boolean verifyExistName(MemberPostDto memberPostDto) {
        Optional<Member> user = memberRepository.findByName(memberPostDto.getName());
        return user.isPresent();
    }
    public String findMemberName(Long userId){
        Optional<Member> findMember = memberRepository.findById(userId);
        Member member = findMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.Member_NOT_FOUND));
        return member.getName();
    }

    public Member getLoginMember() {
        Optional<Member> findMember =memberRepository.findByEmail(SecurityUtil.getCurrentMemberId());
        findMember.orElseThrow(()-> new BusinessLogicException(ExceptionCode.Member_NOT_FOUND));
        Member member = findMember.get();
        return member;
    }
}
