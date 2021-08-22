package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.service.CategoryService;
import com.project.lpd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListProductByCategoryController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping({"/list_product_category"})
    public String categoryProduct(Model model,@RequestParam(value = "name", defaultValue = "")String  name) {
        CategoryEntity categoryEntity = categoryService.getCategoryByName(name);
        List<CategoryEntity> category = categoryService.getAllCategory();
        List<ProductEntity> products = productService.getProductByCategory(categoryEntity);
        model.addAttribute("products", products);
        model.addAttribute("categorys",category);
        return "listproductcategory";
    }
}
