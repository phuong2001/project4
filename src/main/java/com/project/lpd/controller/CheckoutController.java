package com.project.lpd.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.lpd.entity.Card;
import com.project.lpd.entity.CartItemEntity;
import com.project.lpd.entity.OrderEntity;
import com.project.lpd.entity.UserEntity;
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
    PaypalService paypalService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";


    @GetMapping("/pay")
    public String payIndex(Model model){
        model.addAttribute("order", new OrderDto());
        return "pay";
    }
    @PostMapping(value = "/pay")
    public String checkout(Authentication authentication, @ModelAttribute("order")OrderDto orderDto, RedirectAttributes redirectAttributes){
        double totalPrice = 0;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<CartItemEntity> carts = cartService.getCartByUser(userEntity);
        for (CartItemEntity itemcart : carts){
            totalPrice += (itemcart.getSubtotal());
        }
        if( userEntity.getWallet() >= totalPrice){
            orderDto.setStatus("PENDING");
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDescription(orderDto.getDescription());
            orderEntity.setStatus(orderDto.getStatus());
            orderEntity.setPriceTotal(totalPrice);
            orderEntity.setUserid(userEntity.getId());
            orderEntity.setAddress(orderDto.getAddress());
            orderEntity.setFullname(orderDto.getFullname());
            orderEntity.setPhone(orderDto.getPhone());
            userEntity.setWallet(userEntity.getWallet() - totalPrice);
            userService.updateUser(userEntity);
            orderService.createOrder(orderEntity);
            orderItemService.saveOrderItem(userEntity,orderEntity);
            UserEntity admin = userService.getUserByName("admin");
            admin.setWallet(admin.getWallet() + totalPrice);
            userService.updateUser(admin);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error","Not Enough money please add more !");
            return "redirect:/charge";
        }

    }

    @GetMapping("/charge")
    public String chargeIndex(Model model){
        model.addAttribute("order", new Card());
        return "charge";
    }

    @PostMapping("/charge")
    public String payment(@ModelAttribute("order")Card card) {
        try {
            Payment payment = paypalService.createPayment(card.getPrice(), card.getCurrency(),
                    card.getDescription(), "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }


}
