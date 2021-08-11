package com.project.lpd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="active")
public class ActiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activeid;

    @Column(name = "name")
    private String name;

    public ActiveEntity() {
    }


    @JsonIgnore
    @OneToMany(mappedBy = "active")
    private List<ShipperEntity> shipactivate;

    public ActiveEntity(int activeid, String name, List<ShipperEntity> shipactivate) {
        this.activeid = activeid;
        this.name = name;
        this.shipactivate = shipactivate;
    }

    public int getActiveid() {
        return activeid;
    }

    public void setActiveid(int activeid) {
        this.activeid = activeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShipperEntity> getShipactivate() {
        return shipactivate;
    }

    public void setShipactivate(List<ShipperEntity> shipactivate) {
        this.shipactivate = shipactivate;
    }
}