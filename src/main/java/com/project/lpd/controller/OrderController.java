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
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.ArrayList;
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
    @GetMapping("/listorder")
    public String showCart(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<OrderEntity> orderItem = orderService.getOrderByUser(userEntity);
        List<OrderEntity> Items = new ArrayList<>();
        for (OrderEntity ord : orderItem){
            if (ord.getStatus().equals("DELIVERED") || ord.getStatus().equals("PAID")){
                Items.add(ord);
            }
        }
        model.addAttribute("orderItem",Items);
        return "listorder";
    }

    @GetMapping("/orderdetail")
    public String orderUserDetail(Model model, @RequestParam(value = "id", defaultValue = "0") int id){
        OrderEntity order = orderService.getById(id);
        model.addAttribute("ord", order);
        return "orderdetail";
    }

//    //list_admin
//    @GetMapping({"/listorder"})
//    public String ListOrder(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size) {
//        List<OrderEntity> orderItem = orderService.getAllOrder(PageRequest.of(page, size));
//        int totalPage  = orderService.getTotalPage(PageRequest.of(page, size));
//        model.addAttribute("totalPage", totalPage);
//        model.addAttribute("size", size);
//        model.addAttribute("page", page);
//        model.addAttribute("orderItem", orderItem);
//        return "listorder";
//    }

    @PostMapping("/user_confirm")
    public String ConfirmOrder(@ModelAttribute OrderEntity orderEntity, @RequestParam("id") int id){
        OrderEntity order = orderService.getById(id);
        order.setStatus("RECEIVED");
        orderService.saveOrder(order);
        return "redirect:/listorder";
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
    @PostMapping({"/seller_confirm"})
    public String SellerUpdate(@ModelAttribute OrderEntity orderEntity, @RequestParam("id") int id){
        OrderEntity order = orderService.getById(id);
        order.setStatus("DELIVERED");
        orderService.saveOrder(order);
        return "redirect:/buyer_order";
    }
    @GetMapping("/success")
    public String done(){
        return "success";
    }


}
