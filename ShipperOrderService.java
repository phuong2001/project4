package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ShipperEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ShipperOrderService {
    OrderEntity creatMember(OrderEntity orderEntity);
    List<OrderEntity> getAllorder(Pageable pageable);

    Optional<OrderEntity> find(int id);
    OrderEntity getShipbyId(int id);

    int getTotaPage(Pageable pageable);
    List<OrderEntity> getShipper(String name);
}
