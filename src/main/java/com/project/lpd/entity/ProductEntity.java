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
    private int id;
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

    @ManyToOne()
    @JoinColumn(name = "categoryid", insertable = false, updatable = false)
    private CategoryEntity category;

    @ManyToOne()
    @JoinColumn(name = "brandid", insertable = false, updatable = false)
    private BrandEntity brand;

    @ManyToOne()
    @JoinColumn(name = "reviewid", insertable = false, updatable = false)
    private ReviewEntity review;

    @ManyToMany(mappedBy = "product")
    private List<CartEntity> cart;

    @ManyToMany(mappedBy = "product")
    private List<OrderEntity> order;
}
