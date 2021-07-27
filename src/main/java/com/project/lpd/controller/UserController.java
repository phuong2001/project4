package com.project.lpd.controller;

import com.project.lpd.entity.UserEntity;
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
    public String pageableUser(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        List<UserEntity> users = userService.getAllUser(PageRequest.of(page, size));
        int totalPage  = userService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("users", users);
        return "list_user";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            userService.deleteUser(id);
        }
        return "redirect:/list";
    }

    @GetMapping("/update")
    public String viewUpdateUser(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        UserEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute UserEntity userEntity, Model model) {
        userService.updateUser(userEntity);
        return "redirect:/list";
    }

  @PostMapping("/search")
  public String userSearch(@RequestParam String name, Model model) {
      List<UserEntity> users = userService.getUserByFullName(name);
      model.addAttribute("users", users);
      return  "list_user";
  }


}
