package com.org.wallet_app.service;

import com.org.wallet_app.dto.BankAccountRequest;
import com.org.wallet_app.entity.BankAccount;
import com.org.wallet_app.entity.Client;
import com.org.wallet_app.repository.BankAccountRepository;
import com.org.wallet_app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<?> insert(BankAccountRequest dto) {
        Client client;
        Optional<Client> clientOptional = clientRepository.findByNumberIdentityCard(dto.numberCard());
        if (clientOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Client not founded");
        }
        client = clientOptional.get();

        BankAccount account = new BankAccount();
        account.setBalance(dto.balance());
        account.setBankAccountType(dto.bankAccountType());
        account.setClient(client);
        account.setCreatedAt(LocalDateTime.now());
        accountRepository.save(account);
        return ResponseEntity.ok("Account created with success!");
    }


    public ResponseEntity<?> getAll() {

        if (accountRepository.findAll().isEmpty())
            return ResponseEntity.ok("Not account was create");

        return ResponseEntity.ok(accountRepository.findAll());
    }
}
