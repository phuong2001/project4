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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class    OrderController {
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
    public String listOrder(Model model, Authentication authentication,@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(defaultValue = "name") String fullname, @RequestParam(value = "size", defaultValue = "5") int size){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<OrderEntity> orderItem = orderService.getOrderByUser(userEntity);
        List<OrderEntity> Paid = new ArrayList<>();
        List<OrderEntity> Delivered = new ArrayList<>();
        List<OrderEntity> Done = new ArrayList<>();
        List<ProductEntity> products = productService.getProductByFullName(fullname);
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        for (OrderEntity ord : orderItem){
            if (ord.getStatus().equals("PAID")){
                Paid.add(ord);
            } else if (ord.getStatus().equals("DELIVERED")){
                Delivered.add(ord);
            } else if (ord.getStatus().equals("DONE")){
                Done.add(ord);
            }
        }
        model.addAttribute("Paid",Paid);
        model.addAttribute("Delivered",Delivered);
        model.addAttribute("Done",Done);
        return "listorder";
    }



    @GetMapping("/orderdetail")
    public String orderUserDetail(Model model, @RequestParam(value = "id", defaultValue = "0") int id, Authentication authentication){
        OrderEntity order = orderService.getById(id);
        List<OrderItem> orderitem = orderItemService.findByOrders(order);
        model.addAttribute("orderitem",orderitem);
        model.addAttribute("order",order);
        return "orderdetail";
    }

    @PostMapping("/user_confirm")
    public String ConfirmOrder(@ModelAttribute OrderEntity orderEntity, @RequestParam("id") int id ,RedirectAttributes att){
        OrderEntity order = orderService.getById(id);
        order.setStatus("DONE");
        orderService.saveOrder(order);
        att.addFlashAttribute("mess","Your order has been done ! You can check again in order history.");
        List<OrderItem> orderItems = orderItemService.getListOrderItem(order);
        for (OrderItem item : orderItems){
            UserEntity seller = userService.getUserById(item.getProducts().getUserid());
            double total = item.getUnitPrice();
            double sellermoney = total * 98 / 100;
            seller.setWallet(seller.getWallet() + sellermoney);
            userService.updateUser(seller);
            double adminMoney = total * 2 / 100;
            UserEntity admin = userService.getUserByName("admin");
            admin.setWallet(admin.getWallet() + adminMoney);
            userService.updateUser(admin);
        }
        return "redirect:/listorder";
    }

    @GetMapping({"/listOrderProduct"})
    public String listOrderProduct(Model model, Authentication authentication){
        double seller = 0 ;
        List<OrderItem> Paid = new ArrayList<>();
        List<OrderItem> Delivered = new ArrayList<>();
        List<OrderItem> Done = new ArrayList<>();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> products = productService.getProductByUser(userEntity);
        for (ProductEntity product : products){
            List<OrderItem> orderitems = orderItemService.findByProduct(product);
            model.addAttribute("orderitem",orderitems);
            for (OrderItem ord : orderitems){
                double sellerMoney = ord.getUnitPrice() * 98 /100;
                seller = (double) Math.round(sellerMoney*1000) / 1000;
                if (ord.getOrders().getStatus().equals("PAID")){
                    Paid.add(ord);
                } else if (ord.getOrders().getStatus().equals("DELIVERED")){
                    Delivered.add(ord);
                } else if (ord.getOrders().getStatus().equals("DONE")){
                    Done.add(ord);
                }
            }
            model.addAttribute("money", seller);
            model.addAttribute("Paid",Paid);
            model.addAttribute("Delivered",Delivered);
            model.addAttribute("Done",Done);
        }
        return "buyer_order";
    }

    @PostMapping({"/seller_confirm"})
    public String SellerUpdate(@ModelAttribute OrderEntity orderEntity, @RequestParam("id") int id, RedirectAttributes att){
        OrderEntity order = orderService.getById(id);
        order.setStatus("DELIVERED");
        orderService.saveOrder(order);
        att.addFlashAttribute("mess","You has been confirm customer order. We will get your products as soon as possible ! ");
        return "redirect:/listOrderProduct";
    }

    @GetMapping("/success")
    public String done(){
        return "result";
    }


    @PostMapping("/OrderSearch")
    public String indexSearch(Model model, @RequestParam(defaultValue = "name") String fullname, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<ProductEntity> products = productService.getProductByFullName(fullname);
        int totalPage = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products", products);
        return "/listorder";
    }
}
