package com.org.wallet_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "residence")
public class Residence extends BaseEntity{

    @Column(name = "street_avenue")
    private String streetAvenue;

    @Column(name = "number_house", nullable = false)
    private String numberHouse;
}
