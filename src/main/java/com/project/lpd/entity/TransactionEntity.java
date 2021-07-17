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

    @OneToMany(mappedBy = "transaction")
    private List<UserEntity> user;

    @OneToMany(mappedBy = "transaction")
    private List<OrderEntity> order;
}
