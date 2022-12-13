package com.controller;

import com.service.NewsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/news")
public class NewsController {
    private NewsService newsService;
}
