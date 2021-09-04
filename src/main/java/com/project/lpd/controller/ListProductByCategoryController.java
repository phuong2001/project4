package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.service.CategoryService;
import com.project.lpd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListProductByCategoryController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping({"/list_product_category"})
    public String categoryProduct(Model model,@RequestParam(value = "name", defaultValue = "")String  name,@RequestParam   (value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        CategoryEntity categoryEntity = categoryService.getCategoryByName(name);
        List<CategoryEntity> category = categoryService.getAllCategory();
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        List<ProductEntity> products = productService.getProductByCategory(categoryEntity);
        model.addAttribute("products", products);
        model.addAttribute("categorys",category);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        return "listproductcategory";
    }





}
