package com.project.lpd.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="activate")
public class ShipActivateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name = "activeid")
    private int activeid;

    public ShipActivateEntity(int id, String name, double price, int activeid, ActiveEntity active, List<ShipperEntity> shippers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.activeid = activeid;
        this.shippers = shippers;
    }

    public double getPrice() {
        return price;
    }

    public int getActiveid() {
        return activeid;
    }

    public void setActiveid(int activeid) {
        this.activeid = activeid;
    }




    @JsonIgnore
    @OneToMany(mappedBy = "activate")
    private List<ShipperEntity> shippers;

    public ShipActivateEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShipperEntity> getShippers() {
        return shippers;
    }

    public void setShippers(List<ShipperEntity> shippers) {
        this.shippers = shippers;
    }
}
