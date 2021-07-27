package com.project.lpd.controller;

import com.project.lpd.entity.UsersEntity;
import com.project.lpd.service.UsersService;
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
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping({"/users"})
    public String News(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size) {
        List<UsersEntity> productUsers = usersService.getAllProductUser(PageRequest.of(page, size));
        int totalPage  = usersService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("productUsers", productUsers);
        return "users";
    }

    @GetMapping({"/listProductUser"})
    public String pageableNews(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        List<UsersEntity> productUsers = usersService.getAllProductUser(PageRequest.of(page, size));
        int totalPage  = usersService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("productUsers", productUsers);
        return "listProductUser";
    }



    @PostMapping("/createProductUser")
    public String addUser(@ModelAttribute UsersEntity usersEntity, Model model) {
        usersService.createProductUser(usersEntity);
        return "redirect:/listProductUser";
    }

    @GetMapping("/deleteProductUser")
    public String deleteProductUser(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            usersService.deleteProductUser(id);
        }
        return "redirect:/listProductUser";
    }

    @GetMapping("/updateProductUser")
    public String viewUpdateNew(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        UsersEntity productUser = usersService.getUserById(id);
        model.addAttribute("productUser", productUser);
        return "updateProductUser";
    }

    @PostMapping("/updateProductUser")
    public String updateProductUser(@ModelAttribute UsersEntity usersEntity, Model model) {
        usersService.updateProductUser(usersEntity);
        return "redirect:/listProductUser";
    }

    @GetMapping("/createProductUser")
    public String viewAddUser(Model model) {
        UsersEntity usersEntity = new UsersEntity();
        model.addAttribute("model", usersEntity);
        return "createProductUser";
    }
}
