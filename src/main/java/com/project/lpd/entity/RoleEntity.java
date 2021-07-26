package com.project.lpd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleid;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    public RoleEntity(final String name) {
        super();
        this.name = name;
    }

}
