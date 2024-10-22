package com.example.basic.domain.comment.controller;

import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/write")
    public String write(String body, long articleId) {

        commentService.write(articleId, body);
        return "redirect:/article/detail/%d".formatted(articleId);
    }

}
