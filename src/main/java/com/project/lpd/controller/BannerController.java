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

@Controller
public class BannerController {
    @Autowired
    BannerService bannerService;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/upload";

    @GetMapping("/createBanner")
    public String createBannerView( Model model){
        return "BannerCreate";
    }

    @PostMapping("/createBanner")
    public String createBanner(@ModelAttribute BannerEntity banner,
                               @RequestParam("file1") MultipartFile file1, Model model) throws IOException {
        String img1name = file1.getOriginalFilename();
        Path filenamepath1 = Paths.get(uploadDir,img1name);
        Files.write(filenamepath1,file1.getBytes());
        return "Banner";
    }


}
