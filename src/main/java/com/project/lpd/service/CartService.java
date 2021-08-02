package com.project.lpd.service;

import com.project.lpd.entity.CartEntity;

import java.util.List;

public interface CartService {
    CartEntity getCartById(int id);
    CartEntity updateCart(CartEntity cartEntity);
}
