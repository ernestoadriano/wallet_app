package com.org.wallet_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class Person extends BaseEntity{

    @Column(nullable = false)
    protected String fullName;

    protected LocalDate birth;

    @Column(nullable = false, unique = true)
    protected String numberIdentityCard;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
}
