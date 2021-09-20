package com.project.lpd.controller;

import com.project.lpd.entity.*;
import com.project.lpd.model.MapperDto;
import com.project.lpd.model.UserDto;
import com.project.lpd.service.ProductService;
import com.project.lpd.service.RoleService;
import com.project.lpd.service.UserService;
import com.project.lpd.service.UserServiceImpl;
import com.project.lpd.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @Autowired
    NewsService newsService;

    @Autowired
    OrderItemService orderItemService;

    @GetMapping({"/", "/index"})
    public String index(Model model,@RequestParam(value = "name", defaultValue = "")String  name)
    {
        List<CategoryEntity> category = categoryService.getAllCategory();
        CategoryEntity categoryEntity = categoryService.getCategoryByName("Consoles & Accessories");
        List<ProductEntity> product = productService.getConsoleProduct(categoryEntity);
        List<ProductEntity> date = productService.getTopByDate();
        List<NewsEntity> blog = newsService.getBlogByDate();
        List<OrderItem> topOrder = orderItemService.getTopOrder();
        List<ProductEntity> top = productService.getTopPrice();
        List<ProductEntity> random = productService.getRandom();
        model.addAttribute("products", product);
        model.addAttribute("categoryEntity", categoryEntity);
        model.addAttribute("categorys", category);
        model.addAttribute("dates",date);
        model.addAttribute("blogs", blog);
        model.addAttribute("topOrders",topOrder);
        model.addAttribute("top",top);
        model.addAttribute("randoms",random);
        return "index";
    }

    @GetMapping("/adminIndex")
    public String adminindex(Authentication authentication , Model model) {
        double seller =0;
        List<OrderItem> Paid = new ArrayList<>();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        List<ProductEntity> products = productService.getProductCreateByUser(userEntity);
        List<OrderItem> topOrder = orderItemService.getTopOrder();
        int countProduct = productService.CountProduct(userEntity.getId());
        List<ProductEntity> top = productService.getTopProduct();
        int orderDone = orderService.getCountOrderDone(userEntity.getId());
        int orderPaid = orderService.getCountOrderPaid(userEntity.getId());
        int quantityUser = userService.quantityUser();
        int countP = productService.CountProduct();
        List<UserEntity> newUser = userService.getNewUser();
        List<ProductEntity> productpaid = productService.getProductByUsers(userEntity);
        for (ProductEntity product : productpaid) {
            List<OrderItem> orderitems = orderItemService.findByProduct(product);
            model.addAttribute("orderitem", orderitems);
            for (OrderItem ord : orderitems) {
                double sellerMoney = ord.getUnitPrice() * 98 / 100;
                seller = (double) Math.round(sellerMoney * 1000) / 1000;
                if (ord.getOrders().getStatus().equals("PAID")) {
                    Paid.add(ord);
                }
            }
            model.addAttribute("money", seller);
            model.addAttribute("Paid", Paid);
        }
        model.addAttribute("newUsers", newUser);
        model.addAttribute("top",top);
        model.addAttribute("user",userEntity);
        model.addAttribute("topOrders",topOrder);
        model.addAttribute("productcra",products);
        model.addAttribute("product",countProduct);
        model.addAttribute("orderDone",orderDone);
        model.addAttribute("orderPaid",orderPaid);
        model.addAttribute("quantityUser",quantityUser);
        model.addAttribute("products",countP);
        return "AdminIndex";
    }

    @GetMapping("/login")
    public String Login(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "LoginPage";
    }

    @PostMapping("/searchProduct")
    public String indexSearch(Model model , @RequestParam (defaultValue ="name") String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size){
        List<ProductEntity> products = productService.getProductByFullName(name);
        List<CategoryEntity> category = categoryService.getAllCategory();
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products" , products);
        model.addAttribute("categorys", category);
        return "/products";
    }
    @PostMapping("/searchProducts")
    public String adminSearch(Model model , @RequestParam (defaultValue ="name") String name, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size){
        List<ProductEntity> products = productService.getProductByFullName(name);
        List<CategoryEntity> category = categoryService.getAllCategory();
        int totalPage  = productService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("products" , products);
        model.addAttribute("categorys", category);
        return "/productall";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto){
        userService.signUpUser(userDto);
        return "redirect:/index";
    }

    @GetMapping({"/about"})
    public String about2() { return "about"; }

    @GetMapping("/help")
    public String Help(){
        return "help";
    }

    @GetMapping("/error")
    public String ErrorPage(){return "error";}
}
