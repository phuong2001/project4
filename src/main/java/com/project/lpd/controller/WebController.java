package com.project.lpd.controller;

import com.project.lpd.entity.RoleEntity;
import com.project.lpd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class WebController {
    @Autowired
    RoleService roleService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){
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

    @GetMapping("/help")
    public String Help(){
        return "help";
    }

    @GetMapping({"/list"})
    public String pageableRole(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "4") int size) {
        List<RoleEntity> roles = roleService.getAllRole(PageRequest.of(page, size));
        int totalPage  = roleService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("roles", roles);
        return "listrole";
    }

    @GetMapping("/update")
    public String viewUpdateRole(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        RoleEntity role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "updaterole";
    }

    @PostMapping("/update")
    public String updateRole(@ModelAttribute RoleEntity roleEntity, Model model) {
        roleService.updateRole(roleEntity);
        return "redirect:/list";
    }
}
