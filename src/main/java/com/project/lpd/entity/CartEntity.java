package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;

    @Column(name = "buyerId")
    private int buyerId;

    @Column(name = "status")
    private Short status;

    @Column(name = "total")
    private double Total;

    @Column(name = "createAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id",insertable = false, updatable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private ProductEntity products;

}
