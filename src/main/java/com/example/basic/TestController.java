package com.example.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @GetMapping("/test/var")
    public String var() {
        return "test/variable";
    }

    @GetMapping("/test/condition")
    public String condition() {
        return "test/condition";
    }

    @GetMapping("/test/loop")
    public String loop() {
        return "test/loop";
    }
}