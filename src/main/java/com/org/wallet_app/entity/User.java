package com.org.wallet_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.org.wallet_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(nullable = false, unique = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;
}
