package com.example.basic.domain.test;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccounService {

    private final AccountRepository accountRepository;

    @PostConstruct
    private void makeTest() {

        if (accountRepository.findAll().isEmpty()) {

            Account account1 = new Account();
            account1.setOwner("hong");
            account1.setAmount(10000);


            Account account2 = new Account();
            account2.setOwner("kim");
            account2.setAmount(10000);

            accountRepository.save(account1);
            accountRepository.save(account2);
        }
    }

    @Transactional
    public void transfer(long fromId, long toId, int amount) {

        Account fromAccount = accountRepository.findById(fromId).orElseThrow(() -> new RuntimeException("출금 계좌가 존재하지 않습니다."));
        Account toAccount = accountRepository.findById(toId).orElseThrow(() -> new RuntimeException("입금 계좌가 존재하지 않습니다."));

        if (fromAccount.getAmount() < amount) {
            throw new RuntimeException("출금 계좌의 잔액이 부족합니다.");
        }

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        accountRepository.save(fromAccount);

        if (true) {
            throw new RuntimeException("예외 발생");
        }

        toAccount.setAmount(toAccount.getAmount() + amount);


        accountRepository.save(toAccount);

        System.out.println("계좌 이체가 완료되었습니다.");
    }
}
