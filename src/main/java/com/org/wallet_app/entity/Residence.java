package com.org.wallet_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "residences")
@Getter
@Setter
public class Residence extends BaseEntity{

    @Column(name = "street_avenue")
    private String streetAvenue;

    @Column(name = "number_house", nullable = false)
    private String numberHouse;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @Column(name = "client_id")
    private Long clientId;
}
