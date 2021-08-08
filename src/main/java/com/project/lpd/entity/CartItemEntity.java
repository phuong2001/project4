package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cartItem")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "buyerid",insertable = false, updatable = false)
    UserEntity user;

    @Column(name = "productid")
    private int productid;

    @Column(name = "buyerid")
    private int userid;


    @Transient
    public double getSubtotal(){
          return this.product.getPrice() * quantity;
    }

}
