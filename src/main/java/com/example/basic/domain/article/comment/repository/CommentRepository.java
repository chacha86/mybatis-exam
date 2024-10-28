package com.example.basic.domain.article.comment.repository;

import com.example.basic.domain.article.comment.entity.Comment;
import com.example.basic.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthor(Member loginMember);
}
