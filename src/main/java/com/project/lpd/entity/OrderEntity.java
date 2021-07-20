package com.project.lpd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private Short status;
    @Column(name = "total")
    private double toatl;
    @Column(name = "discount")
    private double discount;
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updateAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "order")
    private List<TransactionEntity> transaction;

    @ManyToOne()
    @JoinColumn(name = "shipperid", insertable = false, updatable = false)
    private ShipperEntity shipper;

    @ManyToMany
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<ProductEntity> product;
}
