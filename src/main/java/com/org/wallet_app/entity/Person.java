package com.org.wallet_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@MappedSuperclass
public abstract class Person extends BaseEntity{

    @Column(nullable = false)
    protected String fullName;

    protected LocalDate birth;

    @Column(nullable = false, unique = true)
    protected String numberIdentityCard;
    
}
