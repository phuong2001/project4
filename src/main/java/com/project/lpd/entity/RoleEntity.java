package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data

public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleid;

    @Column(name = "name")
    private String name;

    public RoleEntity(String name) {
        super();
        this.name = name;
    }
}
