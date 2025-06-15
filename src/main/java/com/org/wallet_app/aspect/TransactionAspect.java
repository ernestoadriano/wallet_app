package com.org.wallet_app.aspect;

import com.org.wallet_app.repository.BankAccountRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

    @Autowired
    private BankAccountRepository accountRepository;

}
