package com.project.lpd.entity;

import javax.persistence.*;

@Entity
@Table(name = "shipper")
public class ShipperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "orderid", insertable = false, updatable = false)
    private OrderEntity order;
}
