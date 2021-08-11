package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private int phone;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address",nullable = true)
    private String address;
    @Column(name = "wallet",nullable = true)
    private double wallet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleid"))
    private Collection<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> review;

    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transaction;

    @OneToMany(mappedBy = "user")
    private List<ProductEntity> products;

    public UserEntity(String username,String fullName, String email, String password, Collection<RoleEntity> roles,double wallet) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.username = username;
        this.wallet = wallet;
    }
}
