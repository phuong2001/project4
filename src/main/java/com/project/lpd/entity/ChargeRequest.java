package com.project.lpd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "charge")
public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chargeid;
    @Column(name = "des")
    private String description;
    @Column(name = "amount")
    private int amount;
    @Column(name = "currency")
    private Currency currency;
    @Column(name = "stripeEmail")
    private String stripeEmail;
    @Column(name = "StripeToken")
    private String stripeToken;
}
