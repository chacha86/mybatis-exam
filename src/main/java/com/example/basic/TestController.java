package com.example.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/test/param")
    public String param(Model model) {

        int myNumber = 10;

        model.addAttribute("myNumber", myNumber);

        return "test/param";
    }
}
