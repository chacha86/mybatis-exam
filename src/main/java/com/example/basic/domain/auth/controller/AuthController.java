package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.service.AuthService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.service.MemberService;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final ReqResHandler reqResHandler;
    private final AuthService authService;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) { // 매개변수 - request, response

        session.invalidate();

        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Getter
    @Setter
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, HttpServletResponse response, HttpSession session) {
        Member targetMember = authService.getLoginMember(loginForm.username, loginForm.password);

        if(targetMember == null) {
            return "login-fail";
        }

        reqResHandler.setLoginMember(targetMember);

        return "redirect:/article/list";
    }
}
