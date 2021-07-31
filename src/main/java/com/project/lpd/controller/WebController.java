package com.project.lpd.controller;

import com.project.lpd.entity.RoleEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.MapperDto;
import com.project.lpd.model.UserDto;
import com.project.lpd.service.RoleService;
import com.project.lpd.service.UserService;
import com.project.lpd.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserService userService;



    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping({"/default"})
    public String defaultafterlogin(HttpServletRequest request){
        if(request.isUserInRole("ADMIN")){
            return "redirect:/adminIndex" ;
        }
        return "redirect:/index";
    }

    @GetMapping("/adminIndex")
    public String adminindex() {
        return "AdminIndex";
    }

    @GetMapping("/login")
    public String Login(){return "login";}

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto, Model model){
        userService.signUpUser(userDto);
        return "redirect:/index";
    }

    @GetMapping("/productDetail")
    public String productDetail(){ return "detailProduct";}

    @GetMapping({"/about"})
    public String about2() { return "about"; }

    @GetMapping({"/profile"})
    public String profile() { return "profile"; }

    @GetMapping("/create_product")
    public String createProduct(){
        return "create_product";
    }

    @GetMapping("/cart")
    public String Cart(){
        return "cart";
    }

    @GetMapping("/help")
    public String Help(){
        return "help";
    }



    @GetMapping("/error")
    public String ErrorPage(){return "error";}
}
