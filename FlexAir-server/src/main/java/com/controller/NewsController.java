package com.controller;

import com.model.entity.News;
import com.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {
    private NewsService newsService;
    public NewsController(NewsService newsService){
        super();
        this.newsService = newsService;
    }
    @GetMapping("/news")
    public List<News> getNews(){
        return newsService.getNews();
    }
    @GetMapping("/{id}")
    public News getNewsByID(@PathVariable Integer id){
        return newsService.getNewsByID(id);
    }
}
