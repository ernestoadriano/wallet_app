package com.org.wallet_app.controller;

import com.org.wallet_app.dto.BankAccountRequest;
import com.org.wallet_app.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class BankAccountController {

    @Autowired
    private BankAccountService accountService;

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody BankAccountRequest dto) {
        return accountService.insert(dto);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return accountService.getAll();
    }
}
