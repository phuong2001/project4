package com.project.lpd.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brand")
public class    BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private ProductEntity product;
}
