package com.org.wallet_app.controller;

import com.org.wallet_app.dto.TransactionDTO;
import com.org.wallet_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransactionDTO dto) {
        transactionService.transfer(dto);
    }

    @PostMapping("/payment")
    public void payment(@RequestBody TransactionDTO dto) {
        transactionService.payment(dto);
    }
}
