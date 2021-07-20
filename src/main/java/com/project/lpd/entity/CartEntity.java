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

    @OneToMany(mappedBy = "cart")
    private List<UserEntity> user;

    @ManyToMany
    @JoinTable(name = "product_cast",
            joinColumns = @JoinColumn(name = "cartid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<ProductEntity> product;
}
