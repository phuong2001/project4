package com.project.lpd.controller;

import com.project.lpd.entity.*;
import com.project.lpd.service.OrderItemService;
import com.project.lpd.service.OrderService;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    //list_user
    @GetMapping("/order")
    public String showCart(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<OrderEntity> orderItem = orderService.getOrderByUser(userEntity);
        model.addAttribute("orderItem",orderItem);
        return "order";
    }
    //list_admin
    @GetMapping({"/listorder"})
    public String ListOrder(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size) {
        List<OrderEntity> orderItem = orderService.getAllOrder(PageRequest.of(page, size));
        int totalPage  = orderService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("orderItem", orderItem);
        return "listorder";
    }

    @PostMapping("/user_confirm")
    public String ConfirmOrder(@ModelAttribute OrderEntity orderEntity){
        OrderEntity order = orderService.getById(orderEntity.getOrderid());
        order.setStatus("Done");
        orderService.saveOrder(order);
        return "redirect:/profile";
    }

    @GetMapping({"/listOrderProduct"})
    public String listOrderProduct(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> products = productService.getProductByUser(userEntity);
        for (ProductEntity product : products){
            List<OrderItem> orderitems = orderItemService.findByProduct(product);
            model.addAttribute("orderitem",orderitems);
        }
        return "buyer_order";
    }
    @GetMapping("/success")
    public String done(){
        return "success";
    }


}
