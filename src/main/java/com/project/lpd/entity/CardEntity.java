package com.project.lpd.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "card")
    private List<UserEntity> user;

}
