package com.org.wallet_app.aspect;

import com.org.wallet_app.dto.TransactionDTO;
import com.org.wallet_app.entity.BankAccount;
import com.org.wallet_app.exception.InvalidTransactionException;
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

    @Around("@annotation(jakarta.transaction.Transactional) " +
            "&& execution(* com.org.wallet_app.service.TransactionService.*(..))")
    public void verifyCredentialForTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] object = joinPoint.getArgs();

        if (object != null && object.length > 0) {
            TransactionDTO dto = (TransactionDTO) object[0];

            BankAccount payer = accountRepository.findById(dto.payer().getId())
                    .orElseThrow(() -> new InvalidTransactionException("Not founded"));

            BankAccount payee = accountRepository.findById(dto.payee().getId())
                    .orElseThrow(() -> new InvalidTransactionException("Not founded"));

            validateAccount(payer, payee);

            if (dto.value().compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidTransactionException("The is must be bigger than zero.");
            }

            if (dto.value().compareTo(payer.getBalance()) > 0) {
                throw new InvalidTransactionException("You don't have balance");
            }

            joinPoint.proceed();
        }
    }

    private void validateAccount(BankAccount payer, BankAccount payee) {
        if (Objects.equals(payer.getId(), payee.getId())) {
            throw new InvalidTransactionException("The account is equals");
        }
    }
}
