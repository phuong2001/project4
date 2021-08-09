package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Override
    public OrderEntity createOrder(OrderEntity p) {
        return orderRepo.save(p);
    }
}
