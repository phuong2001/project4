package com.project.lpd.service;

import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.UserEntity;

import java.util.List;

public interface CartService {
    CartItemEntity getCartById(int id);
    List<CartItemEntity> getCartByUser(UserEntity userEntity);
    int AddProductToCart(UserEntity user , int productid, int quantity);
//    double updateQuantity(UserEntity userEntity, int productid, int quantity);
    void RemoveItemFromCart(UserEntity user,int productid);
    void clear();


}
