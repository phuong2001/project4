package com.project.lpd.entity;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "fund")
    private double fund;

    @ManyToOne()
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private CardEntity user;

}
