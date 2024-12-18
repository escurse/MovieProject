package com.escass.movieproject.services;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.exceptions.TransactionalException;
import com.escass.movieproject.mappers.TheaterMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterMapper theaterMapper;

    @Getter
    public enum TheaterCode {
        DAEGU("CGV대구", "0345"),
        SUSEONG("CGV대구수성", "0157"),
        STADIUM("CGV대구스타디움", "0108"),
        ACADEMY("CGV대구아카데미", "0185"),
        YEONGGYEONG("CGV대구연경", "0343"),
        WOLSEONG("CGV대구월성", "0216"),
        JUKJEON("CGV대구죽전", "0256"),
        HANIL("CGV대구한일", "0147"),
        HYUNDAI("CGV대구현대", "0109");

        private final String cgvName;
        private final String cgvCode;

        TheaterCode(String cgvName, String cgvCode) {
            this.cgvName = cgvName;
            this.cgvCode = cgvCode;
        }
    }

    public RegionEntity[] findRegionAll() {
        return this.theaterMapper.getRegionAll();
    }

    public TheaterEntity[] getTheatersByRegion(String region) {
        if (region == null) {
            return null;
        }
        TheaterEntity[] theaters = this.theaterMapper.getTheatersByRegion(region);
        String[] addrs;
        for (TheaterEntity theater : theaters) {
            addrs = theater.getThAddr().split("\n");
            theater.setThAddr(Arrays.toString(addrs));
        }
        return theaters;
    }

    public Pair<Integer, Integer> getTheaterSeatCount(String theater) {
        return Pair.of(this.theaterMapper.getCinemaCountByTheater(theater),
                this.theaterMapper.getSeatCountByCinema(theater));
    }

    @Transactional
    public Map<String, List<String>> Crawl() throws TransactionalException {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver();
        Map<String, List<String>> maps = new HashMap<>();
        try {
            for (TheaterCode theater : TheaterCode.values()) {
                String dateUrl = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=" + theater.cgvCode;
                driver.get(dateUrl);
                WebElement movies = driver.findElement(By.cssSelector("#menu > .last"));
                movies.click();
                List<WebElement> info = driver.findElements(By.cssSelector(".info-contents"));
                for (WebElement infoElement : info) {
                    maps.computeIfAbsent(theater.cgvName, k -> new ArrayList<>()).add(infoElement.getAttribute("outerHTML"));
                }
            }
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return null;
    }
}
