package com.project.lpd.controller;

import com.project.lpd.entity.ShipActivateEntity;
import com.project.lpd.service.ActivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ActivateController {
    @Autowired
    ActivateService activateService;

    @GetMapping("/crete_shipper")
    public String getActivate(Model model){
        List<ShipActivateEntity> activate = activateService.getall();
        model.addAttribute("activate",activate);
        return "/Create_Shipper";
    }
}
