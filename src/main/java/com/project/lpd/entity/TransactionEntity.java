package com.project.lpd.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private Short status;
    @Column(name = "code")
    private String code;
    @Column(name = "content")
    private String content;
    @Column(name = "createAt")
    private Date createdAt;
    @Column(name = "updateAt")
    private Date updatedAt;

    @ManyToOne()
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "orderid", insertable = false, updatable = false)
    private OrderEntity order;
}
