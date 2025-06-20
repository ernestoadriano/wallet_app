package com.org.wallet_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.org.wallet_app.enums.BankAccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
public class BankAccount extends BaseEntity{

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

    public void credit(BigDecimal value) {
        setBalance(getBalance().add(value));
    }
}
