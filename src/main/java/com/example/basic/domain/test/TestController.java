package com.example.basic.domain.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final AccounService accounService;

    @RequestMapping("/transfer")
    @ResponseBody
    public String transfer() {
        accounService.transfer(1, 2, 1000);
        return "계좌 이체 완료";
    }

}
