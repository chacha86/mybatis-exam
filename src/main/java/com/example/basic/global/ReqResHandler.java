package com.example.basic.global;

import com.example.basic.domain.auth.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class ReqResHandler {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public Member getSessionMember() {
        return (Member) request.getSession().getAttribute("loginUser");
    }

    public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie targetCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    targetCookie = cookie;
                }
            }
        }

        return targetCookie;
    }

}
