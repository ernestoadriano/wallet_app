package com.org.wallet_app.dto;

import com.org.wallet_app.entity.BankAccount;

import java.math.BigDecimal;

public record TransactionDTO(BankAccount payer, BankAccount payee, BigDecimal value) {
}
