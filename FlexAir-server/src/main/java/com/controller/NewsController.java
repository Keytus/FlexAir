package com.controller;

import com.model.Message;
import com.model.dto.NewsDTO;
import com.model.entity.News;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateNewsByID(@PathVariable Integer id, @RequestBody NewsDTO newsData){
        News news = newsService.updateNewsByID(id, newsData);
        return new ResponseEntity<>(new Message("success", news.getNewsID()), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> createNews(@RequestBody NewsDTO newsData){
        News news = newsService.createNews(newsData);
        return new ResponseEntity<>(new Message("success", news.getNewsID()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteNewsByID(@PathVariable Integer id){
        newsService.deleteNewsByID(id);
        return new ResponseEntity<>(new Message("success", id), HttpStatus.OK);
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
