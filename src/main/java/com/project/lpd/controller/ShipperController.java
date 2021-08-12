package com.project.lpd.controller;

import com.project.lpd.entity.ShipperEntity;
import com.project.lpd.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class  ShipperController {
    @Autowired
    private ShipperService shipperService;

    @GetMapping("/Shipper_Admin")
    private String listShippera(Model model){
        List<ShipperEntity> shipper1 = shipperService.getAll();
        model.addAttribute("shipper1", shipper1);
        return "Shipper_Admin";
    }

    @PostMapping("/new")
    private String createMember(@ModelAttribute ShipperEntity shipperEntity , Model model){
        shipperService.creatMember(shipperEntity);
        return "redirect:/Shipper_Admin";
    }

    @GetMapping("/Create_Shipper")
    private String user(Model model){
        ShipperEntity shipperEntity = new ShipperEntity();
        model.addAttribute("ship" , shipperEntity);
        return "Create_Shipper";
    }
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable(name = "id") int id ){
        shipperService.deleteById(id);
        return "redirect:/Shipper_Admin";
    }

    @GetMapping("/Edit_Ship/{id}")
    private String find(@PathVariable(name = "id") int id , Model model){
        Optional<ShipperEntity> edit = shipperService.find(id);
        edit.ifPresent(shipperEntity -> model.addAttribute("shipper2" ,shipperEntity));
        return "Edit_Ship";
    }
    @GetMapping("/search_Shipper")
    public String searchShipper(Model model , @RequestParam("name") String name){
        List<ShipperEntity> listShipper = shipperService.findByNameShipper(name);
        model.addAttribute("listShipper" , listShipper );
        //model.addAttribute("name" , name);
        return ("Shipper_Admin");
    }
    //  @GetMapping("/Shipper_Admin/name")
    //private Page<ShipperEntity> getShipper(@RequestParam(value = "page" ,defaultValue = "0" ,required = false)int page,
    //                                  @RequestParam(value="limit", defaultValue = "5", required = false)int limit,
    //                                @RequestParam(value = "name" , required = false)String name){
//Page<ShipperEntity> list = shipperService.findAllByShipper(PageRequest.of(page , limit));
//list.getTotalPages();
    //      return "/Shipper_Admin";
    //}


}
