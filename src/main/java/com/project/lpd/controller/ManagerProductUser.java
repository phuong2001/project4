package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.ProductDto;
import com.project.lpd.repository.ProductRepo;
import com.project.lpd.service.CategoryService;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.project.lpd.controller.ProductController.uploadDir;

@Controller
public class ManagerProductUser {


    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryService categoryService;

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
    @GetMapping("/editProductManager")
    public String viewUpdateNew(Model model, @RequestParam(value = "id", defaultValue = "") int id,Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity user= userService.getUserByName(userDetails.getUsername());
        ProductEntity product = productService.getProductById(id);
        model.addAttribute("user",user);
        model.addAttribute("product", product);
        return "updateProductManager";
    }

    @PostMapping( value = "/editProductManager")
    public String editProductUser(@ModelAttribute("product") @Valid ProductDto productDto,
                                  @RequestParam(value = "id") int id,@RequestParam("filename") MultipartFile file,
                                  @RequestParam("imgname") String imgname,
                                  @RequestParam(value = "categoryid") int cateid,
                                  Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        ProductEntity product = productService.getProductById(id);
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
        product.setUserid(userEntity.getId());
        CategoryEntity category = categoryService.getCategoryById(cateid);
        product.setCategoryid(category.getCategoryid());
        productService.updateProduct(product);
        return "redirect:/listProductManager";
    }



}
