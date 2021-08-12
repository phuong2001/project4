package com.project.lpd.controller;

import com.project.lpd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired
    OrderService orderService;
    @GetMapping({"/profile"})
    public String profile(Model model) {
        model.addAttribute("Order");
        return "profile";
    }


}
