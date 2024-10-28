package com.example.basic.domain.article.article.repository;

import com.example.basic.domain.article.article.entity.Article;
import com.example.basic.domain.article.comment.entity.Comment;
import com.example.basic.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuthor(Member author);

    @Query("select a from Article a join a.commentList c where c = :comment")
    Article findByComment(Comment comment);
}
