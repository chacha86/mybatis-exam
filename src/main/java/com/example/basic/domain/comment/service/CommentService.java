package com.example.basic.domain.comment.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.service.MemberService;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public void write(long articleId, Member author, String body) {
        Article article = articleService.getById(articleId);
        Comment c1 = Comment.builder()
                .article(article)
                .author(author)
                .body(body)
                .build();

        commentRepository.save(c1);
    }
}
