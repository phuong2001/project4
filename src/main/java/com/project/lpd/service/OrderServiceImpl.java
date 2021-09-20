package com.project.lpd.service;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.OrderItem;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.OrderItemRepo;
import com.project.lpd.repository.OrderRepo;
import com.project.lpd.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    ProductRepo productRepo;


    @Override
    public OrderEntity createOrder(OrderEntity p) {
        return orderRepo.save(p);
    }

    @Override
    public OrderEntity saveOrder(OrderEntity p) {
        return orderRepo.save(p);
    }

    @Override
    public List<OrderEntity> getAllOrder(Pageable pageable) {
        return orderRepo.findAll(pageable).getContent();
    }

    @Override
    public List<OrderEntity> getAllOrd() {
        return orderRepo.findAll();
    }

    @Override
    public int getTotalPage(Pageable pageable) {
        return orderRepo.findAll(pageable).getTotalPages();
    }

    @Override
    public List<OrderEntity> getOrderByUser(UserEntity userEntity) {
        return orderRepo.findByUser(userEntity);
    }

    @Override
    public List<OrderEntity> findByOrderItems(OrderItem orderItem) {
        return orderRepo.findByOrderItems(orderItem);
    }

    @Override
    public OrderEntity getById(int id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public List<OrderEntity> getOrderByFullName(String fullname) {
        return orderRepo.findByFullName(fullname);
    }

    @Override
    public List<OrderEntity> geyOrderByStatus(String status) {
        return orderRepo.findByStatus(status);
    }

    @Override
    public int getCountOrderDone(int id) {
        return orderRepo.countAllDone(id);
    }

    @Override
    public int getCountOrderPaid(int id) {
        return orderRepo.countAllPaid(id);
    }

    @Override
    public double TotalDone() {
        return orderRepo.TotalDone();
    }

    @Override
    public double sumPriceUser(int id) {
        return orderRepo.sumPriceUser(id);
    }


//    @Override
//    public List<OrderEntity> getOrderSeller(UserEntity user) {
//        List<ProductEntity> products = productRepo.findByUser(user);
//
//
//        return null;
//    }


}
