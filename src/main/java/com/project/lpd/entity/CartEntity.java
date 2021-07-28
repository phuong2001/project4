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

    @Column(name = "status")
    private Short status;

    @Column(name = "createAt")
    private Date createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private List<ProductEntity> products;

}
