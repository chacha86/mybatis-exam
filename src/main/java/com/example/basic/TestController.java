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
        articleDao.save(title, body);

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
        articleDao.update(id, title, body);

        return "게시물이 성공적으로 수정되었습니다";
    }
}
