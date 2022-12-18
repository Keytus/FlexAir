package com.controller;

import com.model.dto.NewsDTO;
import com.model.entity.News;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @GetMapping("/news")
    public List<News> getNews(){
        return newsService.getNews();
    }
    @GetMapping("/{id}")
    public News getNewsByID(@PathVariable Integer id){
        return newsService.getNewsByID(id);
    }
    @GetMapping("/dto/news")
    public List<NewsDTO> getNewsDTO(){
        List<NewsDTO> newsDTOList = new ArrayList<>();
        List<News> newsList = newsService.getNews();
        for(News news: newsList){
            newsDTOList.add(convertNewsToDTO(news));
        }
        return newsDTOList;
    }

    @GetMapping("/dto/{id}")
    public NewsDTO getNewsDTOByID(@PathVariable Integer id){
        News news = newsService.getNewsByID(id);
        NewsDTO newsDTO = convertNewsToDTO(news);
        return newsDTO;
    }

    private NewsDTO convertNewsToDTO(News news){
        return new NewsDTO(news.getNewsID(), news.getCustomer().getLogin(),
                news.getInfoBlock().getHeader(), news.getInfoBlock().getMain());
    }
}
