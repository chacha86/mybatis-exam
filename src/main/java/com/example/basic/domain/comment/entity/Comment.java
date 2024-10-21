package com.example.basic.domain.comment.entity;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.auth.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Member author;

}
