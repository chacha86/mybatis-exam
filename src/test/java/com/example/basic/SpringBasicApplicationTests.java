package com.example.basic;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.member.entity.Member;
import com.example.basic.domain.auth.member.repository.MemberRepository;
import com.example.basic.domain.test.Account;
import com.example.basic.domain.test.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
class SpringBasicApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    @DisplayName("트랜잭션 테스트")
    @Transactional(rollbackOn = RuntimeException.class)
    @Rollback(false)
    void t13() {
        // hong -> kim 1000원 보내기
        Account hong = accountRepository.findById(1L).get();
        Account kim = accountRepository.findById(2L).get();
        hong.setAmount(hong.getAmount() - 1000); // 1000 감소
        accountRepository.save(hong);
        if(true) {
            throw new RuntimeException("강제로 예외 발생");
        }
        kim.setAmount(kim.getAmount() + 1000); // 1000 증가
        accountRepository.save(kim);
    }
    @Test
    @DisplayName("트랜잭션 테스트 데이터 생성")
    void t12() {
        Account account1 = new Account();
        account1.setOwner("hong");
        account1.setAmount(10000);
        Account account2 = new Account();
        account2.setOwner("kim");
        account2.setAmount(10000);
        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    @Test
    @DisplayName("게시물에서 객체 그래프 탐색으로 회원 정보 가져오기")
    void t11() {
        Article article = articleRepository.findById(1L).get();
        System.out.println(article.getId());
        System.out.println(article.getTitle());
        System.out.println(article.getBody());
        System.out.println(article.getMember().getUsername());
        System.out.println(article.getMember().getRole());
    }

    @Test
    @DisplayName("테스트 데이터 추가")
    void t10() {
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

        Article a1 = Article.builder()
                .title("제목1")
                .body("내용1")
                .member(m1)
                .build();

        Article a2 = Article.builder()
                .title("제목2")
                .body("내용2")
                .member(m1)
                .build();

        Article a3 = Article.builder()
                .title("제목3")
                .body("내용3")
                .member(m2)
                .build();

        articleRepository.save(a1);
        articleRepository.save(a2);
        articleRepository.save(a3);

        System.out.println("==== 테스트 데이터 생성 완료 ====");
    }

    @Test
    @DisplayName("게시물 정보와 게시물 작성자 정보 같이 가져오기")
    void t9() {
        Article article = articleRepository.findById(1L).get();
        System.out.println(article.getId());
        System.out.println(article.getTitle());
        System.out.println(article.getBody());

//        long memberId = article.getMemberId();
//        Member member = memberRepository.findById(memberId).get();

//        System.out.println(member.getUsername());
//        System.out.println(member.getRole());


    }

    @Test
    @DisplayName("Article에 외래키로 memberId를 넣어서 저장")
    void t8() {

        Member m1 = Member.builder()
                .username("hong")
                .password("1234")
                .role("admin")
                .build();

//        Article a1 = Article.builder()
//                .title("테스트 제목1")
//                .body("테스트 내용1")
//                .memberId(1L)
//                .build();

        memberRepository.save(m1);
//        articleRepository.save(a1);

    }


    @Test
    @DisplayName("회원 수정 - save")
    void t7() {

        // 수정할 대상을 먼저 찾아오기

        Optional<Member> memberOpt = memberRepository.findById(1L);
        Member member = memberOpt.get();

        System.out.println(member.getUsername() + " : " + member.getRole());

        member.setRole("admin"); // 엔터티의 값을 바꾸고
        memberRepository.save(member); // 엔터티 다시 저장

    }


    @Test
    @DisplayName("회원 삭제 - delete, deleteById")
    void t6() {

        // id로 삭제
//		memberRepository.deleteById("kim");

        Optional<Member> memberOpt = memberRepository.findById(1L);
        Member member = memberOpt.get();

        // entity로 삭제
        memberRepository.delete(member);
    }


    @Test
    @DisplayName("회원 단건 조회 - findById")
    void t5() {
        Optional<Member> memberOpt = memberRepository.findById(1L);

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();

            System.out.println(member.getUsername());
            System.out.println(member.getPassword());
            System.out.println(member.getRole());
        }


    }

    @Test
    @DisplayName("회원 전체 조회 - findAll")
    void t4() {
        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            System.out.println(member.getUsername());
        }
    }

    @Test
    @DisplayName("회원 저장 - save")
    void t3() {
        Member member1 = Member.builder()
                .username("hong")
                .password("1234")
                .role("admin")
                .build();

        Member member2 = Member.builder()
                .username("kim")
                .password("qwer")
                .role("normal")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
    }

//	@Test
//	void t2() {
//		Member member2 = Member.builder()
//				.username("kim")
//				.password("qwer")
//				.role("normal")
//				.build();
//
//		memberDao.save(member2);
//	}


    @Test
    void t1() {

        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        List<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");
        strList.add("ccc");

        for (int i = 0; i < strList.size(); i++) {
            System.out.println(strList.get(i));
        }

        for (String str : strList) {
            System.out.println(str);
        }

    }

}
