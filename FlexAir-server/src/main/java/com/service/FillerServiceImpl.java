package com.service;

import com.amadeus.Amadeus;
//import com.kwabenaberko.newsapilib.NewsApiClient;
//import com.kwabenaberko.newsapilib.models.Article;
//import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
//import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.model.entity.*;
import com.repository.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FillerServiceImpl implements FillerService{

    @Autowired
    private InfoBlockRepository infoBlockRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SeatSuiteRepository seatSuiteRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Value("${spring.newsAPI.key}")
    private String keyNewsAPI;

    @Value("${spring.amadeus.client.id}")
    private String amadeusClientID;

    @Value("${spring.amadeus.client.secret}")
    private String amadeusClientSecret;

//    @Override
//    public void generateNews(String keyWord, Integer generateCount){
//        List<Customer> contentMakers = customerRepository.findByCustomerType("contentmaker");
//        if (contentMakers.isEmpty()){
//            throw new ResourceNotFoundException("No content makers");
//        }
//        List<News> news = new ArrayList<>();
//
//        NewsApiClient client = new NewsApiClient(keyNewsAPI);
//        client.getEverything(
//                new EverythingRequest.Builder()
//                        .q(keyWord)
//                        .build(),
//                new NewsApiClient.ArticlesResponseCallback() {
//                    @Override
//                    public void onSuccess(ArticleResponse response) {
//                        Integer counter = 0;
//                        for (Article article : response.getArticles()){
//                            if (infoBlockRepository.findByHeader(article.getTitle()).isEmpty() && counter < generateCount){
//                                String html = extractHTML(article.getUrl());
//                                String content = getFullContent(html);
//
//                                InfoBlock generatedInfoBlock = new InfoBlock();
//                                News generatedNews = new News();
//
//                                generatedInfoBlock.setHeader(article.getTitle());
//                                generatedInfoBlock.setMain(content);
//
//                                infoBlockRepository.save(generatedInfoBlock);
//
//                                generatedNews.setInfoBlock(generatedInfoBlock);
//                                generatedNews.setCustomer(contentMakers.get(getRandomNumber(0, contentMakers.size()-1)));
//
//                                newsRepository.save(generatedNews);
//                                news.add(generatedNews);
//                                counter++;
//                            }
//                        }
//                    }
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        throw new ResourceNotFoundException("NewAPI failed");
//                    }
//                }
//        );
//    }

    @Override
    public void generateFlights(Integer count){
        Amadeus amadeus = Amadeus
                .builder("1SAjncKjiZ9XZtJRWOVrI3srp7c4BrYG", "8f7QYADGaOtrKhqY")
                .build();
        List<Track> tracks = trackRepository.findAll();

        if (tracks.isEmpty()){
            throw new ResourceNotFoundException("No tracks");
        }
        for(int counter=0;counter<count;counter++){
            Flight flight = new Flight();
            Timestamp departure = randomDate();
            Timestamp arrival = randomDate(departure);
            flight.setDepartureTime(departure);
            flight.setArrivalTime(arrival);
            flight.setEconomyCost((float) getRandomNumber(100, 200));
            flight.setFirstClassCost((float) getRandomNumber(500, 1000));
            flight.setLuxCost((float) getRandomNumber(2000, 5000));
            SeatSuite seatSuite = new SeatSuite();
            seatSuite.setLuxTotal(getRandomNumber(1, 10));
            seatSuite.setFirstClassTotal(getRandomNumber(2, 6) * 5);
            seatSuite.setEconomyTotal(getRandomNumber(6, 10) * getRandomNumber(10, 15));
            seatSuite.setLuxReserved(0);
            seatSuite.setFirstClassReserved(0);
            seatSuite.setEconomyReserved(0);
            seatSuiteRepository.save(seatSuite);
            flight.setSeatSuite(seatSuite);
            flight.setTrack(tracks.get(getRandomNumber(0, tracks.size()-1)));
            flightRepository.save(flight);
        }
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

    private Timestamp randomDate() {
        Calendar calendar = Calendar.getInstance();
        long startMillis = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, 1);
        long endMillis = calendar.getTimeInMillis();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Timestamp(randomMillisSinceEpoch);
    }
    private Timestamp randomDate(Timestamp start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start.getTime());
        long startMillis = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR, getRandomNumber(4, 12));
        long endMillis = calendar.getTimeInMillis();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Timestamp(randomMillisSinceEpoch);
    }
}
