package com.example.basic.domain.comment.controller;

import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Comment> getItems() {
        return commentService.getItems();
    }
}
