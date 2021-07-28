package com.project.lpd.controller;


import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping({"/listproduct"})
    public String pageableRole(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> products = productService.AllProduct(PageRequest.of(page, size));
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("roles", products);
        return "listproduct";
    }

    @GetMapping("/updateproduct")
    public String viewUpdateRole(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        ProductEntity product = productService.getProductById(id);
        model.addAttribute("role", product);
        return "updateproduct";
    }

    @PostMapping("/updaterole")
    public String updateRole(@ModelAttribute ProductEntity productEntity, Model model) {
        productService.updateProduct(productEntity);
        return "redirect:/listproduct";
    }
}
