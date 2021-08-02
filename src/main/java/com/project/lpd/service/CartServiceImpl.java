package com.project.lpd.service;


import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepo cartRepo;


    @Override
    public CartItemEntity getCartById(int id) {
        return null;
    }

    @Override
    public CartItemEntity updateCart(CartItemEntity cartEntity) {
        return null;
    }

    @Override
    public List<CartItemEntity> getCartByUser(UserEntity userEntity) {
        return cartRepo.findByUser(userEntity);
    }
}
