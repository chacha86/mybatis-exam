package com.example.basic.domain.article.comment.entity;

import com.example.basic.domain.article.article.entity.Article;
import com.example.basic.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Member author;

}
