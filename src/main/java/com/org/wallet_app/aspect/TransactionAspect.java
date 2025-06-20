package com.org.wallet_app.aspect;

import com.org.wallet_app.dto.TransactionDTO;
import com.org.wallet_app.entity.BankAccount;
import com.org.wallet_app.repository.BankAccountRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Aspect
@Component
public class TransactionAspect {

    @Autowired
    private BankAccountRepository accountRepository;

    @Around("@annotation(jakarta.transaction.Transactional) && execution(* com.org.wallet_app.service.Transaction.*(..))")
    public void verifyCredentialForTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] object = joinPoint.getArgs();

        if (object != null && object.length > 0) {
            TransactionDTO dto = (TransactionDTO) object[0];

            BankAccount payer = accountRepository.findById(dto.payer().getId())
                    .orElseThrow(() -> new RuntimeException("Not founded"));

            BankAccount payee = accountRepository.findById(dto.payee().getId())
                    .orElseThrow(() -> new RuntimeException("Not founded"));

            validateAccount(payer, payee);

            if (dto.value().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("You don't have balance");
            }

            if (dto.value().compareTo(payer.getBalance()) > 0) {
                throw new IllegalArgumentException("The is must be bigger than zero.");
            }

            joinPoint.proceed();
        }
    }

    private void validateAccount(BankAccount payer, BankAccount payee) {
        if (Objects.equals(payer.getId(), payee.getId())) {
            throw new RuntimeException("The account is equals");
        }
    }
}
