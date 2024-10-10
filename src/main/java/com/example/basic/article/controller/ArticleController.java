package com.example.basic.article.controller;

import com.example.basic.article.entity.Article;
import com.example.basic.article.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleService.getById(id); // 데이터 처리(비지니스 로직)
        model.addAttribute("article", article); // 웹 관련 처리

        return "article/detail";
    }

    @RequestMapping("/article/list")
    public String list(String loginedMember, Model model) {
        List<Article> articleList = articleService.getAll();

        // 단골이냐 아니냐(쿠폰 여부)
        // 쿠폰 있으면 단골.

        // 쿠폰 없으면 일반.

        System.out.println("loginedMember : " + loginedMember);

        model.addAttribute("articleList", articleList);
        model.addAttribute("loginedUser", loginedMember);
        return "article/list";
    }

    @GetMapping("/article/write")
    public String articleWrite() {
        return "article/write";
    }

    @Getter
    @Setter
    public static class WriteForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/article/write")
    public String write(@Valid WriteForm writeForm, Model model) {

        articleService.write(writeForm.title, writeForm.body);
        return "redirect:/article/list"; // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
    }

    @RequestMapping("/article/delete/{id}")
    public String delete(@PathVariable long id) {

        articleService.deleteById(id);
        return "redirect:/article/list";
    }

    @Getter
    @Setter
    public static class ModifyForm {
        @NotBlank String title;
        @NotBlank  String body;
    }

    @RequestMapping("/article/modify/{id}")
    public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm){
        articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());
        return "redirect:/article/detail/%d".formatted(id); // 브라우저 출력 => html 문자열로 출력
    }

    @RequestMapping("/show-html")
    public String showHtml() {
        return "test"; // .html 확장자를 스프링부트가 자동으로 붙여줌
    }



}
