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

        String URL = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=0157&date=20241107#tile_0";
        Document doc = Jsoup.connect(URL).get();
        System.out.println(doc);
        Elements element = doc.select(".thumb-image");
        System.out.println(element);
    }
}
