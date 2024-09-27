package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ArticleDao articleDao;

    @RequestMapping("/article/detail/{id}")
    @ResponseBody
    public Article detail(@PathVariable("id") long id) {
        Article article = articleDao.findById(id);

        return article;
    }

    @RequestMapping("/article/list")
    @ResponseBody
    public List<Article> list() {
        List<Article> articleList = articleDao.findAll();
        return articleList;
    }

    @RequestMapping("/article/write")
    @ResponseBody
    public String write(String title, String body) {

        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.save(article);

        return "게시물이 성공적으로 저장되었습니다";
    }

    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        articleDao.deleteById(id);

        return "게시물이 성공적으로 삭제되었습니다.";
    }

    @RequestMapping("/article/modify/{id}")
    @ResponseBody
    public String update(@PathVariable("id") long id, String title, String body) {

        // 빌더 방식
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.update(article);

        return "게시물이 성공적으로 수정되었습니다"; // 브라우저 출력 => html 문자열로 출력
    }

    @RequestMapping("/show-html")
    public String showHtml() {
        return "test"; // .html 확장자를 스프링부트가 자동으로 붙여줌
    }
}
