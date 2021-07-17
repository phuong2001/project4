package com.project.lpd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/loginHome")
    public String loginHome(){
        return "login_home";
    }

    @GetMapping("/productDetail")
    public String productDetail(){ return "detailProduct";}



    @GetMapping("/create_product")
    public String createProduct(){
        return "create_product";
    }

}
