package com.project.lpd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int Orderid;
    private String status;
    private double priceTotal;
//    @Column(name = "curency")
//    private String currency;
    private String description;
}
