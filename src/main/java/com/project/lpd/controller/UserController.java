package com.project.lpd.controller;

import com.project.lpd.entity.UserEntity;
import com.project.lpd.model.UserDto;
import com.project.lpd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    

    @GetMapping({"/list"})
    public String pageableUser(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size) {
        List<UserEntity> user = userService.getAllUser(PageRequest.of(page, size));
        int totalPage  = userService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("users", user);
        return "list_user";
    }

 /*   @GetMapping("/delete_user")
    public String deleteUser(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            userService.deleteUser(id);
        }
        return "redirect:/list";
    }*/
 @GetMapping("/delete_user/{id}")
 private String delete(@PathVariable(name = "id") int id ){
     userService.deleteUser(id);
     return "redirect:/list";
 }

    @GetMapping("/update")
    public String viewUpdateUser(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userEntity") UserDto userDto, Model model) {
        UserEntity userEntity = userService.getUserByName(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhone(userDto.getPhone());
        userService.updateUser(userEntity);
        return "redirect:/list";
    }

    @GetMapping("/addfund")
    public String AddfundView(Model model,@RequestParam(value = "id") int id){
        UserEntity userEntity = userService.getUserById(id);
        model.addAttribute("user",userEntity);
        return "addfund";
    }

    @PostMapping("/addfund")
    public String Addfund(@ModelAttribute UserEntity userEntity,@RequestParam("fund") double fund){
        userEntity.setWallet(fund);
        userService.updateUser(userEntity);
        return "redirect:/list";
    }



  @PostMapping("/search")
  public String userSearch(@RequestParam String name, Model model,@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
      List<UserEntity> users = userService.getUserByFullName(name);
      int totalPage = userService.getTotalPage(PageRequest.of(page,size));
      model.addAttribute("totalPage", totalPage);
      model.addAttribute("page",page);
      model.addAttribute("size",size);
      model.addAttribute("users", users);
      return  "list_user";
  }


}
