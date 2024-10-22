package com.example.basic.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyLoginException extends RuntimeException {
    public MyLoginException(String message) {
        super(message);
    }
    public void failProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
