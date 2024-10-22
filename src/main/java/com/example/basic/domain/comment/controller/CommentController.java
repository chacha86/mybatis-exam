package com.example.basic.domain.comment.controller;

import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.HttpSession;
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
    private final ReqResHandler reqResHandler;

    @PostMapping("/write")
    public String write(String body, long articleId) {

        Member member = reqResHandler.getSessionMember();
        commentService.write(articleId, member, body);
        return "redirect:/article/detail/%d".formatted(articleId);
    }

}
