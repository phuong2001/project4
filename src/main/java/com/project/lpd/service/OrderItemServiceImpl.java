package com.project.lpd.service;

import com.project.lpd.entity.*;
import com.project.lpd.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductRepo productRepo;

    @Override
    public OrderItem saveItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    @Override
    public void saveOrderItem(UserEntity userEntity , OrderEntity orderEntity) {
        List<CartItemEntity> carts = cartRepo.findByUser(userEntity);
        for (CartItemEntity cart : carts){
            OrderItem item = new OrderItem();
            item.setProducts(cart.getProduct());
            item.setQuantity(cart.getQuantity());
            item.setUnitPrice(cart.getQuantity() * cart.getProduct().getPrice());
            item.setOrders(orderEntity);
            ProductEntity product = productRepo.getById(cart.getProductid());
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            productRepo.save(product);
            orderItemRepo.save(item);
            cartRepo.deleteById(cart.getCartid());
        }
    }
    @Override
    public List<OrderItem> findByProduct(ProductEntity productEntity) {
        return orderItemRepo.findByProducts(productEntity);
    }

    @Override
    public List<OrderItem> getListOrderItem(OrderEntity orderEntity) {
        return orderItemRepo.findByOrders(orderEntity);
    }

}
