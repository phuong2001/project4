package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Productid;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "discount")
    private double discount;
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updateAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "product")
    private List<CategoryEntity> category;

    @OneToMany(mappedBy = "product")
    private List<BrandEntity> brand;

    @OneToMany(mappedBy = "product")
    private List<ReviewEntity> review;

    @ManyToMany(mappedBy = "product")
    private List<CartEntity> cart;

//    @ManyToMany(mappedBy = "product")
//    private List<OrderEntity> order;

    @ManyToOne()
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private UserEntity user;
}
