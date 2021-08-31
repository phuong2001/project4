package com.project.lpd.controller;

import com.project.lpd.entity.CategoryEntity;
import com.project.lpd.entity.NewsEntity;
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
    public String pageableCategory(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size ) {
        List<CategoryEntity> categorys = categoryService.getAll(PageRequest.of(page,size));
        int totalPage  = categoryService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
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

    @PostMapping("/searchcategorys")
    public String searcha(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size , @RequestParam(defaultValue = "name") String name) {
        List<CategoryEntity> categorys = categoryService.getCategoryByFullName(name);
        int totalPage  = categoryService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("categorys" , categorys);
        return "listcategory";
    }


}
