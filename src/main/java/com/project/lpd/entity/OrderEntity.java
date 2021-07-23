package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Orderid;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "createAt")
    private Date createdAt;

    @OneToMany(mappedBy = "order")
    private List<TransactionEntity> transaction;

//    @ManyToMany
//    @JoinTable(
//            name = "order_product",
//            joinColumns = @JoinColumn(name = "Orderid"),
//            inverseJoinColumns = @JoinColumn(name = "Productid"))
//    List<ProductEntity> products;

    

}
