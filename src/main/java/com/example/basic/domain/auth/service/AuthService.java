package com.example.basic.domain.auth.service;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import com.example.basic.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;

    public Member getLoginMember(String username, String password) {

        Optional<Member> memberOpt = memberService.findByUsername(username); // repository에서 id를 제외한 필드(컬럼)으로 필터링할 때는 myBatis때처럼 메서드 시그니쳐를 만들어야 함.

        if(memberOpt.isEmpty()) {
            throw new RuntimeException("잘못된 회원정보입니다.");
        }

        Member member = memberOpt.get();

        if(!member.getPassword().equals(password)) {
            throw new RuntimeException("잘못된 회원정보입니다.");
        }

        return member;

    }
}
