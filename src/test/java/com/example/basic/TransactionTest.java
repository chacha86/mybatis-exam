package com.example.basic;


import com.example.basic.test.Account;
import com.example.basic.test.AccountRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    void t3() {
        Account a1 = accountRepository.findById(1L).get(); // 홍길동 계좌
        Account a2 = accountRepository.findById(2L).get(); // 임꺽정 계좌


        // 2개의 DB 작업은 모두 성공하거나 모두 실패해야 함. => 트랜잭션
        // 홍길동 계좌에서 1000원 차감
        a1.setAmount(a1.getAmount() - 1000);
        accountRepository.save(a1);

        if(true) {
            throw new RuntimeException("강제로 예외 발생");
        }

        // 임꺽정 계좌에 1000원 증가
        a2.setAmount(a2.getAmount() + 1000);
        accountRepository.save(a2);
    }

    @Test
    @DisplayName("@Transactional 을 설정하지 않은 경우")
    void t2() {
        Account a1 = accountRepository.findById(1L).get(); // 홍길동 계좌
        Account a2 = accountRepository.findById(2L).get(); // 임꺽정 계좌


        // 2개의 DB 작업은 모두 성공하거나 모두 실패해야 함. => 트랜잭션
        // 홍길동 계좌에서 1000원 차감
        a1.setAmount(a1.getAmount() - 1000);
        accountRepository.save(a1);

        if(true) {
            throw new RuntimeException("강제로 예외 발생");
        }

        // 임꺽정 계좌에 1000원 증가
        a2.setAmount(a2.getAmount() + 1000);
        accountRepository.save(a2);
    }

    @Test
    void t1() {
        System.out.println("test1");
    }
}
