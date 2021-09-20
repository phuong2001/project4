package com.project.lpd.controller;


import com.project.lpd.entity.*;
import com.project.lpd.model.MapperDto;
import com.project.lpd.model.ProductDto;
import com.project.lpd.service.CategoryService;
import com.project.lpd.service.ImageService;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import com.project.lpd.ultils.FileUploadUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ImageService imageService;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/upload";

    @GetMapping("/createproduct")
    public String CreateProductForm(Model model) {
        model.addAttribute("category", categoryService.getAllCategory());
        model.addAttribute("productDto", new ProductDto());
        return "createProductUser";
    }

    @PostMapping(value = "/createproduct")
    public String CreateProduct(@ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("files") MultipartFile[] files,
                                @RequestParam(value = "categoryid") int id,
                                Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        ProductEntity product = new ProductEntity();
        List<Image> image = new ArrayList<>();
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setUserid(userEntity.getId());
        CategoryEntity category = categoryService.getCategoryById(id);
        product.setCategoryid(category.getCategoryid());
            for (MultipartFile item : files ){
                {
                        Image Itemimage = new Image();
                        String imageUUID = item.getOriginalFilename();
                        Path filenamePath = Paths.get(uploadDir, imageUUID);
                        Files.write(filenamePath, item.getBytes());
                        Itemimage.setProducts(product);
                        Itemimage.setName(imageUUID);
                        image.add(Itemimage);
                }
            }
            imageService.createImage(image);
            productService.createProduct(product);
        return "redirect:/listproduct";
    }


    @GetMapping({"/products"})
    public String ListProduct(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> product = productService.AllProduct(PageRequest.of(page, size));
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        List<CategoryEntity> category = categoryService.getAllCategory();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", product);
        model.addAttribute("categorys", category);
        return "products";
    }

    @GetMapping({"/productall"})
    public String ListProductAll(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> product = productService.AllProduct(PageRequest.of(page, size));
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", product);
        return "productall";
    }

    @GetMapping({"/listproduct"})
    public String AdminProduct(Model model,Authentication authentication, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> products = productService.getProductByUser(userEntity);
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        return "listproduct";
    }

    @GetMapping("/productdetail")
    public String productDetail(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        ProductEntity product = productService.getProductById(id);
        List<ProductEntity> list = productService.getProductByUser(product.getUser());
        model.addAttribute("product", product);
        model.addAttribute("list",list);
        return "detailproduct";
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

    @PostMapping("/productSearch")
    public String indexSearch(Model model, @RequestParam(defaultValue = "name") String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> products = productService.getProductByFullName(name);
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        return "/listproduct";
    }
    @PostMapping("/productSearchAD")
    public String indexSearchAD(Model model, @RequestParam(defaultValue = "name") String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> products = productService.getProductBy(userEntity,name);
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        return "/listproduct";
    }
}