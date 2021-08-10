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
        CartItemEntity cartItemEntity = cartRepo.getCartByUserAndProduct(user,productEntity);
        if(cartItemEntity != null){
            addQuantity = cartItemEntity.getQuantity() + quantity;
            cartItemEntity.setQuantity(addQuantity);
        } else {
            cartItemEntity = new CartItemEntity();
            cartItemEntity.setQuantity(quantity);
            cartItemEntity.setUserid(user.getId());
            cartItemEntity.setProductid(productid);
        }
        cartRepo.save(cartItemEntity);
        return addQuantity;
    }

//    public double updateQuantity(UserEntity user, int productid, int quantity){
//        ProductEntity product = productRepo.findById(productid);
//        cartRepo.updateQuantityyy(user,product,quantity);
//        double subtotal = product.getPrice() * quantity;
//        return subtotal;
//    }


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
