package com.example.basic.domain.article.entity;

import com.example.basic.domain.auth.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "author_id")
    private Member member;

//    private Member member;
}
