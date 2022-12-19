package com.service;

import com.model.dto.NewsDTO;
import com.model.entity.Customer;
import com.model.entity.InfoBlock;
import com.model.entity.News;
import com.repository.CustomerRepository;
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

    @Autowired
    private InfoBlockRepository infoBlockRepository;

    @Autowired
    private CustomerRepository customerRepository;
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
    @Override
    public void deleteNewsByID(Integer id){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
        newsRepository.delete(news);
        infoBlockRepository.delete(news.getInfoBlock());
    }
    @Override
    public News updateNewsByID(Integer id, NewsDTO newsData){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not exist with id :" + id));
        InfoBlock infoBlock = news.getInfoBlock();
        infoBlock.setMain(newsData.getMain());
        infoBlock.setHeader(newsData.getHeader());

        List<Customer> selectedList = customerRepository.findByLogin(newsData.getLogin());
        if (selectedList.isEmpty()){
            throw new ResourceNotFoundException("No customers with login " + newsData.getLogin());
        }
        Customer customer = selectedList.get(0);
        news.setCustomer(customer);

        infoBlockRepository.save(infoBlock);
        return newsRepository.save(news);
    }
    @Override
    public News createNews(NewsDTO newsData){
        News news = new News();

        List<Customer> selectedList = customerRepository.findByLogin(newsData.getLogin());
        if (selectedList.isEmpty()){
            throw new ResourceNotFoundException("No customers with login " + newsData.getLogin());
        }
        Customer customer = selectedList.get(0);
        news.setCustomer(customer);

        InfoBlock infoBlock = new InfoBlock();
        infoBlock.setMain(newsData.getMain());
        infoBlock.setHeader(newsData.getHeader());
        infoBlockRepository.save(infoBlock);
        news.setInfoBlock(infoBlock);

        return newsRepository.save(news);
    }
}
