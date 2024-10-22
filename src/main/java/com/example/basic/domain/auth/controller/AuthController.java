package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @GetMapping("/logout")
    public String logout(HttpSession session) { // 매개변수 - request, response

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
    public String login(@Valid LoginForm loginForm, HttpSession session) {

        Member targetMember = memberService.getLoginMember(loginForm.username, loginForm.password);
        session.setAttribute("loginUser", targetMember);
        return "redirect:/article/list";
    }

    @ExceptionHandler(MyLoginException.class)
    public void handleNotFoundException(MyLoginException e, HttpServletRequest request, HttpServletResponse response) {
        e.failProcess(request, response);
    }
}
