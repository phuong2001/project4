package com.project.lpd.service;

import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.UserEntity;

import java.util.List;

public interface CartService {
    CartItemEntity getCartById(int id);
    CartItemEntity updateCart(CartItemEntity cartEntity);
    List<CartItemEntity> getCartByUser(UserEntity userEntity);
}
