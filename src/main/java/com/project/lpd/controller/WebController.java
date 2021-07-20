package com.project.lpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginHome(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
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

}
