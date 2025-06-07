package com.org.wallet_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
public class BankAccount extends BaseEntity{

    @Column(nullable = false, unique = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    private BankAccountType bankAccountType;

    public void debit(BigDecimal value) {
        setBalance(getBalance().subtract(value));
    }
}
