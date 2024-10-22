package com.example.basic.global.ReqRes;

import com.example.basic.domain.auth.entity.Member;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ReqResHandler {

    private final HttpSession session;
    private final String LOGIN_KEY = "loginedMember";

    public Member getSessionMember() {
        return (Member)session.getAttribute(LOGIN_KEY);
    }

    public void setSessionMember(Member member) {
        session.setAttribute(LOGIN_KEY, member);
    }
}
