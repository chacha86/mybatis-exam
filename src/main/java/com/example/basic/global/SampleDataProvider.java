package com.example.basic.global;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.member.entity.Member;
import com.example.basic.domain.auth.member.repository.MemberRepository;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class SampleDataProvider implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==== 테스트 데이터 생성 ====");
        Member m1 = Member.builder()
                .username("hong")
                .password("1234")
                .role("Admin")
                .build();

        Member m2 = Member.builder()
                .username("kim")
                .password("qwer")
                .role("Normal")
                .build();

        memberRepository.save(m1);
        memberRepository.save(m2);

        Article a1 = makeTestArticle("제목1", "내용1", m1);
        Article a2 = makeTestArticle("제목2", "내용2", m1);
        Article a3 = makeTestArticle("제목3", "내용3", m2);

        articleRepository.save(a1);
        articleRepository.save(a2);
        articleRepository.save(a3);

        Comment c1 = makeTestComment("댓글1", a1, m1);
        Comment c2 = makeTestComment("댓글2", a1, m1);
        Comment c3 = makeTestComment("댓글3", a1, m2);
        Comment c4 = makeTestComment("댓글4", a1, m2);

        commentRepository.save(c1);
        commentRepository.save(c2);
        commentRepository.save(c3);
        commentRepository.save(c4);

        System.out.println("==== 테스트 데이터 생성 완료 ====");
    }

    private Article makeTestArticle(String title, String body, Member member) {
        return Article.builder()
                .title(title)
                .body(body)
                .member(member)
                .build();
    }

    private Comment makeTestComment(String body, Article article, Member member) {
        return Comment.builder()
                .body(body)
                .article(article)
                .author(member)
                .build();
    }
}
