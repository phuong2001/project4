package com.project.lpd.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.lpd.entity.*;
import com.project.lpd.model.OrderDto;
import com.project.lpd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    ShipperService shipperService;
    @Autowired
    ProductService productService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";


    @GetMapping("/pay")
    public String payIndex(Model model, Authentication authentication){
        double totalPrice = 0;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<CartItemEntity> carts = cartService.getCartByUser(userEntity);
        List<ShipperEntity> ship = shipperService.getAll();
        for (CartItemEntity itemcart : carts){
            totalPrice += (itemcart.getSubtotal());
        }
        model.addAttribute("carts",carts);
        model.addAttribute("total",totalPrice);
        model.addAttribute("order", new OrderDto());
        model.addAttribute("ship",ship);
        return "pay";
    }

    @PostMapping(value = "/pay")
    public String checkout(Authentication authentication, @ModelAttribute("order")OrderDto orderDto, @RequestParam(value = "ship") int ship){
        double totalPrice = 0;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<CartItemEntity> carts = cartService.getCartByUser(userEntity);
        for (CartItemEntity itemcart : carts){
            totalPrice += (itemcart.getSubtotal());
        }
        if( userEntity.getWallet() >= totalPrice){
            orderDto.setStatus("PAID");
            orderDto.setFeeship(ship);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDescription(orderDto.getDescription());
            orderEntity.setStatus(orderDto.getStatus());
            orderEntity.setPriceTotal(totalPrice);
            orderEntity.setUserid(userEntity.getId());
            orderEntity.setAddress(orderDto.getAddress());
            orderEntity.setFullname(orderDto.getFullname());
            orderEntity.setPhone(orderDto.getPhone());
            orderEntity.setSubtotal(totalPrice + orderDto.getFeeship());
            userEntity.setWallet(userEntity.getWallet() - totalPrice);
            for (CartItemEntity itemcart : carts){
                ProductEntity product = productService.getProductById(itemcart.getProductid());
                product.setQuantity(product.getQuantity() - itemcart.getQuantity());
                productService.updateProduct(product);
            }
            userService.updateUser(userEntity);
            orderService.createOrder(orderEntity);
            orderItemService.saveOrderItem(userEntity,orderEntity);

            return "redirect:/success";
        } else {
            return "redirect:/charge";
        }
    }
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

}
