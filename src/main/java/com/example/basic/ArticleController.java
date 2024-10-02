package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleDao articleDao;

    @RequestMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        Article article = articleDao.findById(id);
        model.addAttribute("article", article);

        return "article/detail";
    }

    @RequestMapping("/article/list")
    public String list(Model model) {
        List<Article> articleList = articleDao.findAll();
        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    @GetMapping("/article/write")
    public String articleWrite() {
        return "article/write";
    }

    @PostMapping("/article/write")
    public String write(String title, String body, Model model) {

        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.save(article);
        return "redirect:/article/list"; // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
    }

    @RequestMapping("/article/delete/{id}")
    public String delete(@PathVariable long id) {
        articleDao.deleteById(id);

        return "redirect:/article/list";
    }

    @RequestMapping("/article/modify/{id}")
    public String update(@PathVariable("id") long id, String title, String body) {

        // 빌더 방식
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.update(article);
        return "redirect:/article/detail/%d".formatted(id); // 브라우저 출력 => html 문자열로 출력
    }

    @RequestMapping("/show-html")
    public String showHtml() {
        return "test"; // .html 확장자를 스프링부트가 자동으로 붙여줌
    }



}
