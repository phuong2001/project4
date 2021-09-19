package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "banner")
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bannerid;
    @Column(name = "image")
     private String img;
    @Column(name = "productid")
    private String productid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productid",insertable = false,updatable = false)
    private ProductEntity product;
}
