package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.service.CategoryService;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class SellerProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;


    @GetMapping({"/selleruser"})
    public String sellerProduct(Model model,@RequestParam(value = "username", defaultValue = "")String  username,@RequestParam   (value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        UserEntity userEntity = userService.getUserByName(username);
        List<ProductEntity> products = productService.getProductByUser(userEntity);
        List<CategoryEntity> category = categoryService.getAllCategory();
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("categorys",category);
        model.addAttribute("products", products);
        return "sellerproduct";
    }

}
