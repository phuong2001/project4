package com.project.lpd.controller;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.ProductRepo;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ManagerProductUser {


    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepo;


    @GetMapping("/listProductManager")
    public String showProductByUser(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> product = productService.getProductByUser(userEntity);
        model.addAttribute("products",product);
        return "listProductManager";
    }

    @GetMapping("/deleteProductManager/{id}")
    private String deleteProduct(@PathVariable(name = "id") int id ){
        productService.deleteProduct(id);
        return "redirect:/listProductManager";
    }
/*    @GetMapping("/editProductManager")
    public String viewUpdateNew(Model model, @RequestParam(value = "id", defaultValue = "") int id,Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity user= userService.getUserByName(userDetails.getUsername());
        ProductEntity product = productService.getProductById(id);
        model.addAttribute("user",user);
        model.addAttribute("product", product);
        return "updateProductManager";
    }
    @PostMapping("/editProductManager")
    public String editProductUser(@ModelAttribute ProductEntity productEntity, UserEntity userEntity, Model model,Authentication authentication,@RequestParam(value = "id") int id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity user= userService.getUserById(id);
        productService.updateProduct(productEntity);
        return "redirect:/listProductManager";
    }*/



}
