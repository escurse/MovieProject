package com.escass.movieproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MovieProjectApplication {

//    public static class GettingStarted {
//        @Test
//        public void testGoogleSearch() throws InterruptedException {
//            // Optional. If not specified, WebDriver searches the PATH for chromedriver.
//             System.setProperty("chromedriver.exe", "./chromedriver.exe");
//             WebDriver driver = new ChromeDriver();
//            driver.get("http://www.google.com/");
//            Thread.sleep(5000);  // Let the user actually see something!
//            WebElement searchBox = driver.findElement(By.name("q"));
//            searchBox.sendKeys("ChromeDriver");
//            searchBox.submit();
//            Thread.sleep(5000);  // Let the user actually see something!
//            driver.quit();
//        }
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MovieProjectApplication.class, args);

//         ChromeDriver 경로 설정
//        System.setProperty("chromedriver.exe", "./chromedriver.exe"); // chromedriver.exe 경로 지정
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled"); // 자동화 브라우저 감지 비활성화
//        options.addArguments("--headless"); // 브라우저 창을 띄우지 않고 실행 (옵션)
//
//        // WebDriver 생성
//        WebDriver driver = new ChromeDriver();
//
//        try {
//            // 오늘 날짜 가져오기
//            LocalDate currentDate = LocalDate.now();
//
//            String dateUrl = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=0345";
//            driver.get(dateUrl);
//
//            // 7일 간의 날짜를 반복하며 크롤링
//            for (int i = 0; i < 2; i++) {
//                String date = currentDate.plusDays(i).toString().replace("-", ""); // YYYY-MM-DD 형식의 날짜
//                System.out.println("상영일: " + date);
//
//                // URL에 날짜 파라미터 추가
//                String url = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=0109&date=" + date;
//                // CGV 극장 URL 열기
//                driver.get(url);
//
//
////             iframe 요소 찾기 및 전환
//            WebElement iframe = driver.findElement(By.id("ifrm_movie_time_table"));
//            driver.switchTo().frame(iframe);
//
//            WebElement movies = driver.findElement(By.cssSelector("#menu > .last"));
//            movies.click();
//            List<WebElement> info = driver.findElements(By.cssSelector(".info-contents"));
//            int i = 1;
//            for (WebElement infoElement : info) {
//            System.out.println(i + "번째 하는 중: " + infoElement.getText());
//            i++;
//            }
//
//            // 영화 시간표 요소 가져오기
//            List<WebElement> movieElements = driver.findElements(By.cssSelector("#slider > .item-wrap.on > .item > li"));
//
//            for (WebElement movieElement : movieElements) {
//                // 영화 제목 추출
//                String movie = movieElement.findElement(By.cssSelector("a")).getAttribute("href");
//                if (movie.isEmpty()) {
//                    continue;
//                }
//                // URL에서 쿼리 파라미터 추출
//                URL url = new URL(movie);
//                String query = url.getQuery();
//
//                // 쿼리 파라미터가 없다면 건너뜁니다.
//                if (query == null || query.isEmpty()) {
//                    continue;
//                }
//
//                // 쿼리 파라미터 분리
//                Map<String, String> queryParams = new HashMap<>();
//                String[] pairs = query.split("&");
//
//                // 각 파라미터에 대해 처리
//                for (String pair : pairs) {
//                    String[] keyValue = pair.split("=");
//
//                    // '='가 없거나 keyValue의 길이가 2가 아니라면 건너뜁니다.
//                    if (keyValue.length == 2) {
//                        queryParams.put(keyValue[0], keyValue[1]);
//                    }
//                }
//
//                // 'date' 파라미터 값 추출
//                String date = queryParams.get("date");
//
//                // date 출력
//                if (date != null) {
//                    System.out.println(date);  // 예: 20241216
//                }
//
//                // movie URL 출력
//                System.out.println(movie);
//            }
//
//
//                    // 상영 시간표 추출
//                    List<WebElement> timeTables = movieElement.findElements(By.cssSelector(".type-hall"));
//                    StringBuilder timeTable = new StringBuilder();
//                    for (WebElement table : timeTables) {
//                        List<WebElement> cinemas = table.findElements(By.cssSelector(".info-hall > ul > li:nth-child(2)"));
//                        for (WebElement cinema : cinemas) {
//                            timeTable.append("상영관: ").append(cinema.getText())
//                                    .append("\n");
//                        }
//                        List<WebElement> timeElements = table.findElements(By.cssSelector(".info-timetable > ul > li > a > em"));
//                        for (WebElement element : timeElements) {
//                            timeTable.append("상영 시간: ").append(element.getText()).append("\n");
//                        }
//                    }
//
//                    // 출력
//                    System.out.println("------------");
//                    System.out.println("영화: " + movieTitle);
//                    System.out.println(timeTable.toString().trim());
//                }
//                System.out.println("------------");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 브라우저 닫기
//            driver.quit();
//        }


        // ChromeDriver 경로 설정
//        System.setProperty("chromedriver.exe", "./chromedriver.exe"); // chromedriver.exe 경로 지정
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled"); // 자동화 브라우저 감지 비활성화
//        options.addArguments("--headless"); // 브라우저 창을 띄우지 않고 실행 (옵션)

        // WebDriver 생성
//        WebDriver driver = new ChromeDriver();

        try {
            // URL에 날짜 파라미터 추가
//            String url = "https://www.cgv.co.kr/rules/service.aspx";
            // CGV 극장 URL 열기
//            driver.get(url);


            // iframe 요소 찾기 및 전환
//            WebElement iframe = driver.findElement(By.id("ifrm_movie_time_table"));
//            driver.switchTo().frame(iframe);

            // 영화 시간표 요소 가져오기
//            WebElement movieElements = driver.findElement(By.cssSelector(".edit_box"));
//            System.out.println(movieElements.getAttribute("outerHTML"));

//            for (WebElement movieElement : movieElements) {
//                // 영화 제목 추출
//                String movieTitle = movieElement.findElement(By.cssSelector(".info-movie > a > strong")).getText();
//
//                //                    String cinema = movieElement.findElement(By.cssSelector(".info-hall > ul > li:nth-child(2)")).getText();
//
//                // 상영 시간표 추출
//                List<WebElement> timeTables = movieElement.findElements(By.cssSelector(".type-hall"));
//                StringBuilder timeTable = new StringBuilder();
//                for (WebElement table : timeTables) {
//                    List<WebElement> cinemas = table.findElements(By.cssSelector(".info-hall > ul > li:nth-child(2)"));
//                    for (WebElement cinema : cinemas) {
//                        timeTable.append("상영관: ").append(cinema.getText())
//                                .append("\n");
//                    }
//                    List<WebElement> timeElements = table.findElements(By.cssSelector(".info-timetable > ul > li > a > em"));
//                    for (WebElement element : timeElements) {
//                        timeTable.append("상영 시간: ").append(element.getText()).append("\n");
//                    }
//                }
//
//                // 출력
//                System.out.println("------------");
//                System.out.println("영화: " + movieTitle);
//                System.out.println(timeTable.toString().trim());
//            }
            System.out.println("------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 브라우저 닫기
//            driver.quit();
        }

//        String URL = "https://www.cgv.co.kr/rules/privacy.aspx";
//        Document doc = Jsoup.connect(URL).get();
//        Elements element = doc.select("#privateview");
//        System.out.println(element);
    }
    // div class="info-alert"


}
//        String URL = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=0109&date=20241210";
//        Document doc = Jsoup.connect(URL).get();
//        Elements element = doc.select("div.info-alert");
//        System.out.println(element);
//        System.out.println(doc);
// div class="info-alert"
