package com.escass.movieproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieProjectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieProjectApplication.class, args);

//        String URL = "http://www.cgv.co.kr/theaters/";
//        Document doc = Jsoup.connect(URL).get();
//        Elements element = doc.select(".title");
//        System.out.println(element);
    }
}
