package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.repository.ShipperOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperOrderServiceImpl implements ShipperOrderService {
    @Autowired
    ShipperOrderRepo shipperOrderRepo;

    @Override
    public OrderEntity creatMember(OrderEntity orderEntity) {
        return null;
    }

    @Override
    public List<OrderEntity> getAllorder(Pageable pageable) {
        return shipperOrderRepo.findAll(pageable).getContent();
    }

    @Override
    public Optional<OrderEntity> find(int id) {
        return Optional.empty();
    }

    @Override
    public OrderEntity getShipbyId(int id) {
        return null;
    }

    @Override
    public int getTotaPage(Pageable pageable) {
        return 0;
    }

    @Override
    public List<OrderEntity> getShipper(String name) {
        return null;
    }
}
