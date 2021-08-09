package com.project.lpd.controller;


import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.service.CartService;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;



    @GetMapping("/cart")
    public String showCart(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<CartItemEntity> cartItem = cartService.getCartByUser(userEntity);
        model.addAttribute("cartItem",cartItem);
        return "cart";
    }

    @PostMapping("/addcart")
    public String addCart(@RequestParam(value = "pid") int productid , @RequestParam(value = "qty" ,defaultValue = "1") int quantity, Authentication authentication ){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        cartService.AddProductToCart(userEntity,productid,quantity);

        return "redirect:/cart";
    }

/*
    @PostMapping("/updatecart")
    public String updateCart(@PathVariable("pid") int productid, @PathVariable("qty") int editQuantity ){
        cartService.UpdateCart(productid,editQuantity);
        return "cart";
    }

 */
    @GetMapping("/removecart")
    public String removeCart(@RequestParam(value = "id" ,defaultValue = "0") int productid , Authentication authentication ){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        cartService.RemoveItemFromCart(userEntity, productid);
        return "redirect:/cart";
    }




}
