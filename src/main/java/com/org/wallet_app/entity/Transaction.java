package com.org.wallet_app.entity;

import com.org.wallet_app.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction extends BaseEntity{

    
    @ManyToOne
    @JoinColumn(name = "payer")
    private BankAccount payer;

    @ManyToOne
    @JoinColumn(name = "payee")
    private BankAccount payee;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Column(nullable = false)
    private BigDecimal value;

}
