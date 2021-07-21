package com.project.lpd.controller;

import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.MapperDto;
import com.project.lpd.model.UserDto;
import com.project.lpd.service.UserService;
import com.project.lpd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class WebController {
    @Autowired
    UserService userService;

    MapperDto mapperDto;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/adminIndex")
    public String adminindex() {
        return "AdminIndex";
    }

    @GetMapping("/login")
    public String loginHome(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody UserDto userDto){
        userService.signUpUser(userDto);
        UserEntity userEntity = mapperDto.convertToEntity(userDto);
        return "redirect:/index";
    }

    @GetMapping("/productDetail")
    public String productDetail(){ return "detailProduct";}

    @GetMapping({"/about"})
    public String about2() { return "about"; }

    @GetMapping({"/products"})
    public String product() { return "products"; }

    @GetMapping({"/pay"})
    public String pay() { return "pay"; }

    @GetMapping({"/profile"})
    public String profile() { return "profile"; }

    @GetMapping("/create_product")
    public String createProduct(){
        return "create_product";
    }

    @GetMapping("/cart")
    public String Cart(){
        return "cart";
    }

    @GetMapping("/news")
    public String News(){
        return "news";
    }

    @GetMapping("/help")
    public String Help(){
        return "help";
    }

}
