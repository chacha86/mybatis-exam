package com.example.basic.domain.article.article.entity;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.article.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
//    private long memberId; // 회원 번호 (외래키)

    @ManyToOne
    private Member author;

    @OneToMany
    @JoinColumn(name="article_id")
    List<Comment> commentList = new ArrayList<>();

}
