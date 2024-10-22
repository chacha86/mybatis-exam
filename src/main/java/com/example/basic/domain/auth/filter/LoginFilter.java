package com.example.basic.domain.auth.filter;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.ReqRes.ReqResHandler;
import jakarta.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    private final ReqResHandler reqResHandler;

    public LoginFilter(ReqResHandler reqResHandler) {
        this.reqResHandler = reqResHandler;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Member member = reqResHandler.getSessionMember();

        if(member == null) {
            throw new RuntimeException("로그인 해야만 사용 가능합니다.");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
