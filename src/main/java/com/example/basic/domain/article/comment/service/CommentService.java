package com.example.basic.domain.article.comment.service;

import com.example.basic.domain.article.article.entity.Article;
import com.example.basic.domain.article.article.service.ArticleService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.article.comment.entity.Comment;
import com.example.basic.domain.article.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public void write(String body, long articleId, Member author) {

        Article article = articleService.getById(articleId);


        Comment c1 = Comment.builder()
                .body(body)
//                .article(article)
                .author(author)
                .build();
        commentRepository.save(c1);

        article.getCommentList().add(c1);
        articleService.update(article);

    }

    public void update(long commentId, String body) {
        Comment comment = getById(commentId);
        comment.setBody(body);
        commentRepository.save(comment);
    }

    public Comment getById(long id) {
        Optional<Comment> commentOpt = commentRepository.findById(id);

        if(commentOpt.isEmpty()) {
            throw new RuntimeException("없는 데이터입니다.");
        }

        return commentOpt.get();
    }

    public void deleteById(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getItemsByAuthor(Member loginMember) {
        return commentRepository.findByAuthor(loginMember);
    }

    public Article getParentById(long commentId) {
        Comment comment = getById(commentId);
        Article article = articleService.getByComment(comment);
        return article;
    }
}
