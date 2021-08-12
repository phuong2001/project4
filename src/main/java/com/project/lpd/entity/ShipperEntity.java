package com.project.lpd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name="activateid")
    private int activateid;
    @Column(name = "activeid")
    private int activeid;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="activeid" ,insertable = false ,updatable = false)
    private ActiveEntity active;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "activateid" , insertable = false ,updatable = false)
    private ShipActivateEntity activate;

    public ShipperEntity() {
    }

    public ShipperEntity(int id, String name, String phone, int activateid, int activeid, ActiveEntity active, ShipActivateEntity activate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.activateid = activateid;
        this.activeid = activeid;
        this.active = active;
        this.activate = activate;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getActivateid() {
        return activateid;
    }

    public void setActivateid(int activateid) {
        this.activateid = activateid;
    }

    public ShipActivateEntity getActivate() {
        return activate;
    }

    public void setActivate(ShipActivateEntity activate) {
        this.activate = activate;
    }
//    @OneToMany(mappedBy = "shipper")
//    private List<OrderEntity> order;

    public int getActiveid() {
        return activeid;
    }

    public void setActiveid(int activeid) {
        this.activeid = activeid;
    }

    public ActiveEntity getActive() {
        return active;
    }

    public void setActive(ActiveEntity active) {
        this.active = active;
    }
}