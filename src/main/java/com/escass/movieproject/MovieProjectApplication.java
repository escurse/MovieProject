package com.escass.movieproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieProjectApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieProjectApplication.class, args);

        String URL = "http://www.cgv.co.kr/movies/detail-view/still-cut.aspx?midx=88459#menu";
        Document doc = Jsoup.connect(URL).get();
        System.out.println(doc);
        Elements elements = doc.getElementsByTag("div");
        for (Element element : elements) {
            Elements elements1 = doc.getElementsByTag("a");
        }
    }
}
