package com.org.wallet_app.service;

import com.org.wallet_app.dto.TransactionDTO;
import com.org.wallet_app.entity.BankAccount;
import com.org.wallet_app.entity.Transaction;
import com.org.wallet_app.enums.TypeTransaction;
import com.org.wallet_app.enums.BankAccountType;
import com.org.wallet_app.exception.InvalidTransactionException;
import com.org.wallet_app.exception.NotFoundEntityException;
import com.org.wallet_app.repository.BankAccountRepository;
import com.org.wallet_app.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository accountRepository;

    @Transactional
    public void transfer(TransactionDTO dto) {

        Transaction transaction = new Transaction();

        BankAccount payer = accountRepository.findById(dto.payer().getId())
                .orElseThrow(() -> new NotFoundEntityException("Not founded"));

        BankAccount payee = accountRepository.findById(dto.payee().getId())
                .orElseThrow(() -> new NotFoundEntityException("Not founded"));
        validateTransferenceTransaction(payer, payee);
        payer.debit(dto.value());
        payee.credit(dto.value());

        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setTypeTransaction(TypeTransaction.TRANSFERENCE);
        transaction.setPayer(dto.payer());
        transaction.setPayee(dto.payee());
        transaction.setValue(dto.value());

        transactionRepository.save(transaction);
        accountRepository.save(payer);
        accountRepository.save(payee);
    }

    @Transactional
    public void payment(TransactionDTO dto) {

        Transaction transaction = new Transaction();
        BankAccount payer = accountRepository.findById(dto.payer().getId())
                .orElseThrow(() -> new NotFoundEntityException("Not founded"));

        BankAccount payee = accountRepository.findById(dto.payee().getId())
                .orElseThrow(() -> new NotFoundEntityException("Not founded"));
        validatePaymentTransaction(payer, payee);

        payer.debit(dto.value());
        payee.credit(dto.value());

        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setTypeTransaction(TypeTransaction.PAYMENT);
        transaction.setPayer(dto.payer());
        transaction.setPayee(dto.payee());
        transaction.setValue(dto.value());

        transactionRepository.save(transaction);
        accountRepository.save(payer);
        accountRepository.save(payee);
    }

    private void validateTransferenceTransaction(BankAccount payer, BankAccount payee) {
        if ((payer.getBankAccountType().equals(BankAccountType.COMMON)
                && payee.getBankAccountType().equals(BankAccountType.ENTERPRISE))
        || (payer.getBankAccountType().equals(BankAccountType.ENTERPRISE)
                && payee.getBankAccountType().equals(BankAccountType.ENTERPRISE)))
            throw new InvalidTransactionException("Transaction is not viable");
    }

    private void validatePaymentTransaction(BankAccount payer, BankAccount payee) {

        if (!(payer.getBankAccountType().equals(BankAccountType.COMMON)
                && payee.getBankAccountType().equals(BankAccountType.ENTERPRISE)))
            throw new InvalidTransactionException("Transaction is not viable.");

    }
}
