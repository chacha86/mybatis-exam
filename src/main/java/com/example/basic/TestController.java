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


    @RequestMapping("/t8/{id}") // id는 결정될 수 없는 값이기 때문에 변수화한다.
    @ResponseBody
    public Article t8(@PathVariable("id")  long id) { // @PathVariable("변수명") -> url에 포함된 정보를 메서드에서 사용 가능
        System.out.println(id);

        Article article = articleDao.findById(id);

        return article;
    }

    @RequestMapping("/t7")
    @ResponseBody
    public Article t7(long id) {
        Article article = articleDao.findById(id);

        return article;
    }

    @RequestMapping("/t6")
    @ResponseBody
    public List<Article> t6() {
        List<Article> articleList = articleDao.findAll();
        return articleList;
    }

    @RequestMapping("/t5")
    @ResponseBody
    public String save(String title, String body) {
        articleDao.save(title, body);

        return "게시물이 성공적으로 저장되었습니다";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/t2")
    @ResponseBody
    public Integer t2(Integer num) {

        return num * 2;
    }

    @RequestMapping("/t3")
    @ResponseBody
    public Article t3() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("title1");
        article.setBody("body1");

        return article;
    }

    @RequestMapping("/t4")
    @ResponseBody
    public List<Article> t4() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("title1");
        article.setBody("body1");

        Article article2 = new Article();
        article2.setId(2L);
        article2.setTitle("title2");
        article2.setBody("body2");

        List<Article> articleList = new ArrayList<>() {{
            add(article);
            add(article2);
        }};

        return articleList;
    }


}
