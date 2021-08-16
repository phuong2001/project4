package com.project.lpd.controller;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class SellerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    @GetMapping("/sellerproduct")
    public String showListProductUser(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> product = productService.getProductByUser(userEntity);
        model.addAttribute("products",product);
        return "sellerproduct";
    }
}
