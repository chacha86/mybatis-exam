package com.example.basic.domain.article.entity;

import com.example.basic.domain.auth.member.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
//    private long memberId; // 회원 번호

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member member;

    @OneToMany
    @JoinColumn(name = "article_id")
    private List<Comment> comments;

}