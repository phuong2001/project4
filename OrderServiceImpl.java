package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.OrderItem;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.OrderItemRepo;
import com.project.lpd.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public OrderEntity createOrder(OrderEntity p) {
        return orderRepo.save(p);
    }

    @Override
    public List<OrderEntity> getAllOrder(Pageable pageable) {
        return orderRepo.findAll(pageable).getContent();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return orderRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public List<OrderEntity> getOrderByUser(UserEntity userEntity) {
        return orderRepo.findByUser(userEntity);
    }




}
