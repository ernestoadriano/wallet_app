package com.org.wallet_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@Getter
@Setter
public class Phone extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JoinColumn(name = "residence_id")
    private Residence residence;
}
