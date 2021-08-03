package com.project.lpd.controller;

import com.project.lpd.entity.CartEntity;
import com.project.lpd.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class HistoryController {


    @Autowired
    HistoryService historyService;
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable(name = "id") int id ){
        historyService.deleteById(id);
        return "redirect:/history_admin";
    }

    @GetMapping("/history_admin")
    public String payhistory(Model model){
        List<CartEntity> history1 = historyService.getAll();
        model.addAttribute("history1", history1);
        return "history_admin";

    }
}
