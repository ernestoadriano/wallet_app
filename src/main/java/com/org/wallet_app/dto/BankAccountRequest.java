package com.org.wallet_app.dto;

import com.org.wallet_app.enums.BankAccountType;

import java.math.BigDecimal;

public record BankAccountRequest(BigDecimal balance, String numberCard, BankAccountType bankAccountType) {
}
