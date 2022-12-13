package com.service;

import com.model.entity.News;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface NewsService {
    public List<News> getNews();
    public News getNewsByID(Integer id);
}
