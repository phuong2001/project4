package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.NewsEntity;
import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping({"/listcategory"})
    public String pageableCategory(Model model) {
        List<CategoryEntity> categorys = categoryService.getAllCategory();
        model.addAttribute("categorys", categorys);
        return "listcategory";
    }

    @GetMapping("/createcategory")
    public String viewAddCategory(Model model) {
        CategoryEntity categoryEntity = new CategoryEntity();
        model.addAttribute("model", categoryEntity);
        return "createcategory";
    }

    @PostMapping("/createcategory")
    public String addCategory(@ModelAttribute CategoryEntity categoryEntity) {
        categoryService.createCategory(categoryEntity);
        return "redirect:/listcategory";
    }
//    @GetMapping("/product")
//    public String viewlist(Model model){
//        List<CategoryEntity> listcate1 = categoryService.getAllList();
//        model.addAttribute("listcate1" , listcate1);
//        return "products";
//
//    }

    @GetMapping("/deletecategory")
    public String deleteCategory(@RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            categoryService.deleteCategory(id);
        }
        return "redirect:/listcategory";
    }

    @GetMapping("/updatecategory")
    public String viewUpdateCategoey(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        CategoryEntity categorys = categoryService.getCategoryById(id);
        model.addAttribute("categorys", categorys);
        return "updatecategory";
    }

    @PostMapping("/updatecategory")
    public String updateCategory(@ModelAttribute CategoryEntity categoryEntity, Model model) {
        categoryService.updateCategory(categoryEntity);
        return "redirect:/listcategory";
    }

    @PostMapping("/searchcategory")
    private String getsearch(@RequestParam String name, Model model){
        List<CategoryEntity> categorys = categoryService.getCaterorybyFullName(name);
        model.addAttribute("categorys" , categorys);
        return "listcategory";
    }
}
