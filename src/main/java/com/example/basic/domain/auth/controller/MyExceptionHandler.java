package com.example.basic.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyExceptionHandler {

    private final AuthController authController;
    public void loginExceptinHandler() {
        try {
            authController.login();
        } catch (Exception e) {
            System.out.println("로그인 실패");
        }
    }
}
