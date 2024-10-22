package com.example.basic.global.init;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInitalizer implements ApplicationRunner {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Member m1 = Member.builder()
                .username("hong")
                .password("1234")
                .role("admin")
                .build();

        Member m2 = Member.builder()
                .username("kim")
                .password("qwer")
                .role("normal")
                .build();

        memberRepository.save(m1);
        memberRepository.save(m2);

        Member author = m1;
        for(int i = 1; i <= 10; i++) {
            author = m1;
            if(i % 2 == 0) {
                author = m2;
            }
            Article article = Article.builder()
                    .title("제목_" + i)
                    .body("내용_" + i)
                    .author(author)
                    .build();
            articleRepository.save(article);
        }
    }
    private Member makeTestMember(String id, String pw, String role) {
        return Member.builder()
                .username(id)
                .password(pw)
                .role(role)
                .build();
    }

    private Article makeTestArticle(String title, String body, Member author) {
        return Article.builder()
                .title(title)
                .body(body)
                .author(author)
                .build();
    }
}
