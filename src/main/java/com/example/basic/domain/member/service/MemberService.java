package com.example.basic.domain.member.service;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getByUsernameOrNull(String username) {

        Optional<Member> memberOpt = memberRepository.findByUsername(username);

        if(memberOpt.isEmpty()) {
            return null;
        }

        return memberOpt.get();
    }

}
