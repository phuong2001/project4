package com.project.lpd.controller;

import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.service.ShipperOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShipperOrderController {
    @Autowired
    ShipperOrderService shipperOrderService;

    @GetMapping("/ShipperOrder")
    private String listShippera(Model model , @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size){
        List<OrderEntity> shipperorder1 = shipperOrderService.getAllorder(PageRequest.of(page ,size));
        int totalPage = shipperOrderService.getTotaPage(PageRequest.of(page ,size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("shipperorder1", shipperorder1);
        return "ShipperOrder";
    }

}
