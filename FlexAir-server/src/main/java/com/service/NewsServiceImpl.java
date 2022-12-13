package com.service;

import com.model.entity.News;
import com.repository.InfoBlockRepository;
import com.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<News> getNews(){
        return newsRepository.findAll();
    }
    @Override
    public News getNewsByID(Integer id){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
        return news;
    }
}
