package com.example.basic.domain.comment.service;

import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void write(String body) {
        Comment c1 = Comment.builder()
                .body(body)
                .build();

        commentRepository.save(c1);
    }
}
