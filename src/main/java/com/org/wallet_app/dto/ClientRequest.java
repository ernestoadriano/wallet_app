package com.org.wallet_app.dto;

import java.time.LocalDate;

public record ClientRequest(String fullName, LocalDate birth, String numberIdentityCard, String phoneNumber) {
}
