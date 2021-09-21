package com.project.lpd.controller;


import com.project.lpd.entity.BannerEntity;
import com.project.lpd.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.List;

@Controller
public class BannerController {
    @Autowired
    BannerService bannerService;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/upload/banner";

    @GetMapping("/createBanner")
    public String createBannerView( Model model){
        model.addAttribute("Banner",new BannerEntity());
        return "Banner";
    }

    @PostMapping("/createBanner")
    public String createBanner(@ModelAttribute BannerEntity banner,
                               @RequestParam("file") MultipartFile file1) throws IOException {
        String img1name = file1.getOriginalFilename();
        Path filenamepath1 = Paths.get(uploadDir,img1name);
        Files.write(filenamepath1,file1.getBytes());
        banner.setImg(img1name);
        bannerService.create(banner);
        return "updateBanner";
    }

    @GetMapping("/updateBanner")
    public String viewUpdateBanner(@ModelAttribute BannerEntity bannerEntity,Model model){
        List<BannerEntity> banners = bannerService.showAll();
        model.addAttribute("Banner", new BannerEntity());
        model.addAttribute("List",banners);
        return "updateBanner";
    }

    @PostMapping("/updateBanner")
    public String updateBanner(@ModelAttribute BannerEntity banner , @RequestParam("bannerid") int id,
                               @RequestParam("file") MultipartFile file) throws IOException{
        BannerEntity bannerEntity = bannerService.getBannerById(id);
        String imgname = file.getOriginalFilename();
        Path filenamepath = Paths.get(uploadDir,imgname);
        Files.write(filenamepath,file.getBytes());
        bannerEntity.setImg(imgname);
        bannerService.updateBanner(bannerEntity);
        return "Banner";
    }


}
