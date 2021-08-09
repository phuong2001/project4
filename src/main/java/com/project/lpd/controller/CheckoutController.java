package com.project.lpd.controller;

import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.OrderDto;
import com.project.lpd.service.CartService;
import com.project.lpd.service.OrderItemService;
import com.project.lpd.service.OrderService;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CheckoutController {
    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/pay")
    public String payIndex(Model model){
        model.addAttribute("order", new OrderDto());
        return "pay";
    }
    @PostMapping(value = "/pay")
    public String checkout(Authentication authentication, @ModelAttribute("order")OrderDto orderDto){
        double totalPrice = 0;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<CartItemEntity> carts = cartService.getCartByUser(userEntity);
        for (CartItemEntity itemcart : carts){
            totalPrice += (itemcart.getSubtotal());
        }
        orderDto.setStatus("PENDING");
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDescription(orderDto.getDescription());
        orderEntity.setStatus(orderDto.getStatus());
        orderEntity.setPriceTotal(totalPrice);
        orderEntity.setUserid(userEntity.getId());
        orderService.createOrder(orderEntity);
        orderItemService.saveOrderItem(userEntity,orderEntity);
        return "redirect:/profile";
    }


}
