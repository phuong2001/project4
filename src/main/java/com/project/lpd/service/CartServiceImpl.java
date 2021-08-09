package com.project.lpd.service;


import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.CartRepo;
import com.project.lpd.repository.ProductRepo;
import com.project.lpd.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;


    @Override
    public CartItemEntity getCartById(int id) {
        return cartRepo.getById(id);
    }


    @Override
    public List<CartItemEntity> getCartByUser(UserEntity userEntity) {
        return cartRepo.findByUser(userEntity);
    }

    @Override
    public int AddProductToCart(UserEntity user, int productid, int quantity) {
        int addQuantity = quantity;
        ProductEntity productEntity = productRepo.findById(productid);
        CartItemEntity cartItemEntity = cartRepo.getCartByUserAndProduct(user,productEntity);
        if(cartItemEntity != null){
            addQuantity = cartItemEntity.getQuantity() + quantity;
            cartItemEntity.setQuantity(addQuantity);
            cartItemEntity.setSubtotal(addQuantity * productEntity.getPrice());
        } else {
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setQuantity(quantity);
            cartItemEntity.setUserid(user.getId());
            cartItemEntity.setProductid(productid);
            cartItemEntity.setSubtotal(quantity * productEntity.getPrice());
        }
        cartRepo.save(cartItemEntity);
        return addQuantity;
    }
/*
    public void UpdateCart(UserEntity user, int productid, int editQuantity){
        ProductEntity productEntity = productRepo.findById(productid);
        CartItemEntity cartItemEntity = cartRepo.getCartByUserAndProduct(user,productEntity);
        cartItemEntity.setQuantity(editQuantity);
    }

 */

    @Override
    public void RemoveItemFromCart(UserEntity user,int productid){
        ProductEntity productEntity = productRepo.findById(productid);
        cartRepo.deleteByUserAndProduct(user, productEntity);
    }

    @Override
    public void clear(){
        cartRepo.deleteAll();
    }
}
