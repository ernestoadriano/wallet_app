package com.org.wallet_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@NoArgsConstructor
public class Client extends Person{

    @OneToMany(mappedBy = "client")
    private List<BankAccount> accounts;
}
