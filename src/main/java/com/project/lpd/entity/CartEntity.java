package com.project.lpd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Short status;
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updateAt")
    private Date updatedAt;

    @ManyToMany
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "productid"), inverseJoinColumns = @JoinColumn(name = "cartid"))
    private List<ProductEntity> product;

    @ManyToOne()
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private CartEntity user;
}
