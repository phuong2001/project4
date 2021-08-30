package com.project.lpd.controller;

import com.project.lpd.entity.*;
import com.project.lpd.service.OrderService;
import com.project.lpd.service.RoleService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

  /*  @GetMapping({"/profile"})
    public String profile(Model model) {
        model.addAttribute("Order");
        return "profile";
    }*/

    @GetMapping("/profile")
    public String showUser(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        model.addAttribute("userEntity",userEntity);
        return "profile";
    }

    @PostMapping("/updateprofile/{id}")
    public String updateUserP(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        userService.updateUserProfile(userEntity);
        return "redirect:/profile";
    }
}
