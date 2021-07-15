package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updateAt")
    private Date updatedAt;
}
