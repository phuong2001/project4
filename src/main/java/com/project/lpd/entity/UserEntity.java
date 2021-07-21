package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private int phone;
    @Column(name = "roleid")
    private int roleid;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    private RoleEntity role;

    @ManyToOne()
    @JoinColumn(name = "cartid", insertable = false, updatable = false)
    private CartEntity cart;

    @ManyToOne()
    @JoinColumn(name = "cardid", insertable = false, updatable = false)
    private CardEntity card;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> review;

    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transaction;

    @OneToMany(mappedBy = "user")
    private List<ProductEntity> product;
}
