package com.example.basic.domain.comment.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/write")
    public String write(String body, long articleId) {

        commentService.write(body, articleId);

        return "redirect:/article/detail/%d".formatted(articleId);
    }

    @PostMapping("/modify/{commentId}")
    public String modify(@PathVariable("commentId") long commentId, long articleId, String body) {
        commentService.update(commentId, body);
        return "redirect:/article/detail/%d".formatted(articleId);
    }

    @PostMapping("/delete/{commentId}")
    public String delete(@PathVariable("commentId") long commendId, long articleId) {
        commentService.deleteById(commendId);

        return "redirect:/article/detail/%d".formatted(articleId);
    }
}
