package com.project.lpd.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "star")
    private int star;

    @OneToMany(mappedBy = "review")
    private List<ProductEntity> product;

    @OneToMany(mappedBy = "review")
    private List<UserEntity> user;
}
