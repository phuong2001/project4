package com.project.lpd.controller;


import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.model.ProductDto;

import com.project.lpd.service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ProductUserController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
    ProductUserService productUserService;

    @GetMapping("/createproductuser")
    public String CreateProductForm(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "createproductuser";
    }
    @PostMapping(value = "/createproductuser")
    public String CreateProduct(@ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("filename") MultipartFile file,
                                @RequestParam("imgname") String imgname) throws IOException {
       ProductEntity product = new ProductEntity();
       product.setName(productDto.getName());
       product.setQuantity(productDto.getQuantity());
       product.setPrice(productDto.getPrice());
       product.setDescription(productDto.getDescription());
       String imageUUID;
       if(!file.isEmpty()){
           imageUUID = file.getOriginalFilename();
           Path filenamePath = Paths.get(uploadDir, imageUUID);
           Files.write(filenamePath,file.getBytes());
       } else {
           imageUUID = imgname;
       }
       product.setImage(imageUUID);
       productUserService.createProduct(product);
       return "redirect:/listproductuser";

    }

    @GetMapping({"/listproductuser"})
    public String AdminProduct(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> products = productUserService.AllProduct(PageRequest.of(page, size));
        int totalPage  = productUserService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        return "listproductuser";
    }


    @GetMapping("/updateproductuser")
    public String viewupdateProduct(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        ProductEntity product = productUserService.getProductById(id);
        model.addAttribute("role", product);
        return "updateproductuser";
    }

    @PostMapping("/updateproductuser")
    public String updateProduct(@ModelAttribute ProductEntity productEntity, Model model) {
        productUserService.updateProduct(productEntity);
        return "redirect:/listproductuser";
    }

    @GetMapping("/deleteproductuser")
    public String deleteProduct(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            productUserService.deleteProduct(id);
        }
        return "redirect:/listproductuser";
    }
}
