package com.org.wallet_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
