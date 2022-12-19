package com.service;

import com.model.dto.NewsDTO;
import com.model.entity.News;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface NewsService {
    public List<News> getNews();
    public News getNewsByID(Integer id);
    public void deleteNewsByID(Integer id);
    public News updateNewsByID(Integer id, NewsDTO newsData);
    public News createNews(NewsDTO newsData);
}
