package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.RoleEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.MapperDto;
import com.project.lpd.model.UserDto;
import com.project.lpd.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/index"})
    public String index(Model model , @RequestParam (value = "name" ,defaultValue = "") String name)
    {
        List<CategoryEntity> category = categoryService.getAllCategory();
        model.addAttribute("categorys", category);
        return "index";

    }

    @GetMapping("/adminIndex")
    public String adminindex() {
        return "AdminIndex";
    }


    @GetMapping("/login")
    public String Login(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "LoginPage";
    }


    @PostMapping("/searchProduct")
    public String indexSearch(Model model , @RequestParam (defaultValue ="name") String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size){
        List<ProductEntity> products = productService.getProductByFullName(name);
        List<CategoryEntity> category = categoryService.getAllCategory();
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products" , products);
        model.addAttribute("categorys", category);
        return "/products";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto, Model model){
        userService.signUpUser(userDto);
        return "redirect:/index";
    }

    @GetMapping({"/about"})
    public String about2() { return "about"; }



    @GetMapping("/create_product")
    public String createProduct(){
        return "create_product";
    }

    @GetMapping("/help")
    public String Help(){
        return "help";
    }



    @GetMapping("/error")
    public String ErrorPage(){return "error";}
}
