package com.example.basic.domain.member.service;

import com.example.basic.domain.article.article.entity.Article;
import com.example.basic.domain.article.article.service.ArticleService;
import com.example.basic.domain.article.comment.entity.Comment;
import com.example.basic.domain.article.comment.service.CommentService;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ArticleService articleService;
    private final CommentService commentService;

    public List<Article> getArticlesByAuthor(Member author) {
        return articleService.getArticlesByAuthor(author);
    }

    public Member getByUsernameOrNull(String username) {

        Optional<Member> memberOpt = memberRepository.findByUsername(username);

        if (memberOpt.isEmpty()) {
            return null;
        }

        return memberOpt.get();
    }

    public List<Comment> getCommentByAuthor(Member loginMember) {
        return commentService.getItemsByAuthor(loginMember);
    }
}
