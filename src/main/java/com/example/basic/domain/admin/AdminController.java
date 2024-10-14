package com.example.basic.domain.admin;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ReqResHandler reqResHandler;

    @GetMapping("/admin/main")
    public String main(HttpServletRequest request, Model model) {
        Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

        if(targetCookie == null) {
            throw new RuntimeException("관리자 계정으로 로그인 해야만 사용 가능합니다.");
        }

        Cookie role = reqResHandler.getCookieByName(request, "role");

        model.addAttribute("role", role.getValue()); // 웹 관련 처리
        model.addAttribute("loginedUser", targetCookie.getValue());
        return "admin/main";
    }

}
