package com.example.basic;


import com.example.basic.test.Account;
import com.example.basic.test.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("계좌 테스트 데이터 생성")
    void t4() {

        Account a1 = Account.builder()
                .owner("홍길동")
                .amount(20000)
                .build();

        Account a2 = Account.builder()
                .owner("임꺽정")
                .amount(10000)
                .build();

        accountRepository.save(a1);
        accountRepository.save(a2);
    }

    @Test
    @DisplayName("@Transactional 을 설정을 한 경우")
    void t3() {

    }

    @Test
    @DisplayName("@Transactional 을 설정하지 않은 경우")
    void t2() {

    }

    @Test
    void t1() {
        System.out.println("test1");
    }
}
