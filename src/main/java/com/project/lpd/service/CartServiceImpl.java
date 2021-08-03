package com.project.lpd.service;


import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.CartRepo;
import com.project.lpd.repository.ProductRepo;
import com.project.lpd.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;


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

    @Override
    public int AddProductToCart(UserEntity user, int productid, int quantity) {
        int addQuantity = quantity;
        ProductEntity productEntity = productRepo.findById(productid);
        CartItemEntity cartItemEntity = cartRepo.getCartByCustomerAndProduct(user,productEntity);
        if(cartItemEntity != null){
            addQuantity = cartItemEntity.getQuantity() + quantity;
            cartItemEntity.setQuantity(addQuantity);
        } else {
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setQuantity(quantity);
            cartItemEntity.setUser(user);
            cartItemEntity.setProduct(productEntity);
        }
        cartRepo.save(cartItemEntity);
        return addQuantity;
    }


}
