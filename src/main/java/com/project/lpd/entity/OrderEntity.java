package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Orderid;
    @Column(name = "status")
    private String status;
    @Column(name = "price_total")
    private double priceTotal;
    @Column(name = "phone")
    private String phone;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "subtotal")
    private double subtotal;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "userid")
    private int userid;
    @ManyToOne
    @JoinColumn(name = "userid",insertable = false, updatable = false)
    UserEntity user;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems;

//    @ManyToMany
//    @JoinTable(
//            name = "order_product",
//            joinColumns = @JoinColumn(name = "Orderid"),
//            inverseJoinColumns = @JoinColumn(name = "Productid"))
//    List<ProductEntity> products;

    

}
