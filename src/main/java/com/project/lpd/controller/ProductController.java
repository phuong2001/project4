package com.project.lpd.controller;


import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.service.ProductService;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final String UPLOAD_DIR = "images/";

    @Autowired
    ProductService productService;

    @GetMapping("/createproduct")
    public String CreateProductForm(Model model) {
        ProductEntity productEntity= new ProductEntity();
        model.addAttribute("model", productEntity);
        return "createnew";
    }
    @PostMapping("/createproduct")
    public String CreateProduct(ProductEntity p, @RequestParam("image") MultipartFile file,Model model) throws IOException {
        String imagename = StringUtils.cleanPath(file.getOriginalFilename());
        p.setFile(imagename);
        productService.createProduct(p);
        String uploadDir = "/images" + p.getProductid();
        return "createnew";
    }

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

    @PostMapping("/updateproduct")
    public String updateRole(@ModelAttribute ProductEntity productEntity, Model model) {
        productService.updateProduct(productEntity);
        return "redirect:/listproduct";
    }
}
