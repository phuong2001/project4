package com.project.lpd.controller;

import com.project.lpd.entity.NewsEntity;
import com.project.lpd.service.NewsService;
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
public class NewsController{
    @Autowired
    NewsService newsService;

    @GetMapping({"/news"})
    public String News(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size) {
        List<NewsEntity> gameNews = newsService.getAllNews(PageRequest.of(page, size));
        int totalPage  = newsService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("gameNews", gameNews);
        return "news";
    }

    @GetMapping("/detailnews")
    public String Detail(Model model, @RequestParam(value = "id", defaultValue = "0") int id){
        NewsEntity news = newsService.getNewById(id);
        model.addAttribute("news", news);
        return "detailnews";
    }


    @GetMapping({"/listnew"})
    public String pageableNews(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        List<NewsEntity> gameNews = newsService.getAllNews(PageRequest.of(page, size));
        int totalPage  = newsService.getTotalPage(PageRequest.of(page, size));
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("gameNews", gameNews);
        return "listnew";
    }

    @GetMapping("/createnew")
    public String viewAddNew(Model model) {
        NewsEntity newsEntity = new NewsEntity();
        model.addAttribute("model", newsEntity);
        return "createnew";
    }

    @PostMapping("/createnew")
    public String addNew(@ModelAttribute NewsEntity newsEntity, Model model) {
        newsService.createNew(newsEntity);
        return "redirect:/listnew";
    }

    @GetMapping("/deletenew")
    public String deleteNew(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id != 0) {
            newsService.deleteNew(id);
        }
        return "redirect:/listnew";
    }

    @GetMapping("/updatenew")
    public String viewUpdateNew(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        NewsEntity gameNew = newsService.getNewById(id);
        model.addAttribute("gameNew", gameNew);
        return "updatenew";
    }

    @PostMapping("/updatenew")
    public String updateNew(@ModelAttribute NewsEntity newsEntity, Model model) {
        newsService.updateNew(newsEntity);
        return "redirect:/listnew";
    }


}
