package com.service;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.model.entity.Customer;
import com.model.entity.InfoBlock;
import com.model.entity.News;
import com.repository.CustomerRepository;
import com.repository.InfoBlockRepository;
import com.repository.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FillerServiceImpl implements FillerService{

    @Autowired
    InfoBlockRepository infoBlockRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void generateNews(String keyWord, Integer generateCount){
        List<Customer> contentMakers = customerRepository.findByCustomerType("contentmaker");
        if (contentMakers.isEmpty()){
            throw new ResourceNotFoundException("No content makers");
        }
        List<News> news = new ArrayList<>();

        String key = "36be80b2da384cbf8a64e0fd8c4d5719";
        NewsApiClient client = new NewsApiClient(key);
        client.getEverything(
                new EverythingRequest.Builder()
                        .q(keyWord)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        Integer counter = 0;
                        for (Article article : response.getArticles()){
                            if (infoBlockRepository.findByHeader(article.getTitle()).isEmpty() && counter < generateCount){
                                String html = extractHTML(article.getUrl());
                                String content = getFullContent(html);

                                InfoBlock generatedInfoBlock = new InfoBlock();
                                News generatedNews = new News();

                                generatedInfoBlock.setHeader(article.getTitle());
                                generatedInfoBlock.setMain(content);

                                infoBlockRepository.save(generatedInfoBlock);

                                generatedNews.setInfoBlock(generatedInfoBlock);
                                generatedNews.setCustomer(contentMakers.get(getRandomNumber(0, contentMakers.size()-1)));

                                newsRepository.save(generatedNews);
                                news.add(generatedNews);
                                counter++;
                            }
                        }
                    }
                    @Override
                    public void onFailure(Throwable throwable) {
                        throw new ResourceNotFoundException("NewAPI failed");
                    }
                }
        );
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private String extractHTML(String url){
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return content;
    }
    private String getFullContent(String html ){
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        String text = body.text();
        return text;
    }
}
