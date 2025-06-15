package com.org.wallet_app.service;

import com.org.wallet_app.entity.Transaction;
import com.org.wallet_app.entity.TypeTransaction;
import com.org.wallet_app.repository.BankAccountRepository;
import com.org.wallet_app.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository accountRepository;

    @Transactional
    public void transfer(Transaction transaction) {

    }
}
