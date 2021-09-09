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
        model.addAttribute("products", product);
        model.addAttribute("categoryEntity", categoryEntity);
        model.addAttribute("categorys", category);
        model.addAttribute("dates",date);
        model.addAttribute("blogs", blog);
        model.addAttribute("topOrders",topOrder);
        model.addAttribute("top",top);
        return "index";
    }

    @GetMapping("/adminIndex")
    public String adminindex(Authentication authentication , Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userService.getUserByName(userDetails.getUsername());
        int countProduct = productService.CountProduct(userEntity.getId());
        model.addAttribute("user",userEntity);
        model.addAttribute("product",countProduct);
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

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto, Model model){
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
