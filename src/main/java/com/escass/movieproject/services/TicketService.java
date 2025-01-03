package com.escass.movieproject.services;

import com.escass.movieproject.entities.CinemaEntity;
import com.escass.movieproject.entities.MovieEntity;
import com.escass.movieproject.entities.ScreenEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.exceptions.TransactionalException;
import com.escass.movieproject.mappers.TicketMapper;
import com.escass.movieproject.vos.MovieVo;
import com.escass.movieproject.vos.RegionVo;
import com.escass.movieproject.vos.ScreenVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketMapper ticketMapper;

    // 시간 정보를 찾아오는 것.
    public ScreenVo[] selectScreenDatesByMovieAndTheaterAndDate(String moTitle, String thName, String scStartDate) {
        if (moTitle == null || moTitle.isEmpty() || moTitle.length() > 100 ||
            thName == null || thName.isEmpty() || thName.length() > 30 ||
            scStartDate == null || scStartDate.isEmpty() || scStartDate.length() > 10) {
            return null;
        }
        TheaterEntity theater = this.ticketMapper.selectTheater(thName);
        MovieEntity movie = this.ticketMapper.selectMovieNumByMovieTitle(moTitle);
        return this.ticketMapper.selectScreenDatesByMovieAndTheaterAndDate(movie.getMoNum(), theater.getThNum(), scStartDate);
    }

    public MovieVo[] selectAllMovies(String moTitle) {
        if (moTitle == null || moTitle.isEmpty()) {
            return null;
        }
        return this.ticketMapper.selectAllMoviesByMoTitle(moTitle);
    }

    public MovieVo[] selectAllMoviesByThName(String thName) {
        if (thName == null || thName.isEmpty()) {
            return null;
        }
        return this.ticketMapper.selectAllMoviesByThName(thName);
    }

    public MovieVo[] selectAllMoviesByScStartDate(String scStartDate) {
        if (scStartDate == null || scStartDate.isEmpty()) {
            return null;
        }
        return this.ticketMapper.selectAllMoviesByScStartDate(scStartDate);
    }

    public MovieVo[] selectAllMoviesByMoTitleAndScStartDate(String moTitle, String scStartDate) {
        if (moTitle == null || moTitle.isEmpty() ||
                scStartDate == null || scStartDate.isEmpty()) {
            return null;
        }
        return this.ticketMapper.selectAllMoviesByMoTitleAndScStartDate(moTitle, scStartDate);
    }

    public MovieVo[] selectAllMoviesByThNameAndScStartDate(String thName, String scStartDate) {
        if (thName == null || thName.isEmpty() ||
                scStartDate == null || scStartDate.isEmpty()) {
            return null;
        }
        return this.ticketMapper.selectAllMoviesByThNameAndScStartDate(thName, scStartDate);
    }

    public MovieVo[] selectAllMoviesByRating() {
        MovieVo[] movies = this.ticketMapper.selectAllMoviesByRating();
        for (MovieVo movie : movies) {
            switch (movie.getRaGrade()) {
                case "청소년관람불가" -> movie.setRaGrade("nineteen");
                case "15세이상관람가" -> movie.setRaGrade("fifteen");
                case "12세이상관람가" -> movie.setRaGrade("twelve");
                case "전체관람가" -> movie.setRaGrade("all");
                case "미정" -> movie.setRaGrade("none");
            }
        }
        return movies;
    }

    public MovieVo[] selectAllMoviesByKorea() {
        MovieVo[] movies = this.ticketMapper.selectAllMoviesByKorea();
        for (MovieVo movie : movies) {
            switch (movie.getRaGrade()) {
                case "청소년관람불가" -> movie.setRaGrade("nineteen");
                case "15세이상관람가" -> movie.setRaGrade("fifteen");
                case "12세이상관람가" -> movie.setRaGrade("twelve");
                case "전체관람가" -> movie.setRaGrade("all");
                case "미정" -> movie.setRaGrade("none");
            }
        }
        return movies;
    }

    public Map<Set<String>, Set<Set<String>>> selectShowTimesByMoTitle(String movie) {
        MovieVo[] movies = this.ticketMapper.selectShowTimesByMoTitle(movie);
        Map<Set<String>, Set<Set<String>>> map = new LinkedHashMap<>();
        Set<String> keys = new LinkedHashSet<>();
        Set<Set<String>> values = new LinkedHashSet<>();
        Set<String> genres = new LinkedHashSet<>();
        Set<String> citNames = new LinkedHashSet<>();
        for (MovieVo movieVo : movies) {
            keys.add(movieVo.getMoTitle());
            keys.add(movieVo.getMoDate());
            keys.add(String.valueOf(movieVo.getMoTime()));
            keys.add(movieVo.getMImgUrl());
            genres.add(movieVo.getGeName());
            citNames.add(movieVo.getCitName());
        }
        values.add(genres);
        values.add(citNames);
        map.computeIfAbsent(keys, k -> new LinkedHashSet<>()).addAll(values);
        return map;
    }

    public RegionVo[] selectRegionAndTheaterCount() {
        return this.ticketMapper.selectRegionAndTheaterCount();
    }


    public Map<String, String> getWeekdaysByMoTitleAndThName(String moTitle, String thName) {
        // 화면의 시작 날짜들을 가져옴
        MovieVo[] screens = this.ticketMapper.selectAllMoviesByMoTitleAndThName(moTitle, thName);

        // 고유 날짜를 저장할 Set
        SortedSet<String> sortedSet = new TreeSet<>();

        // 날짜 리스트를 돌면서 고유 날짜만 저장
        for (MovieVo screen : screens) {
            sortedSet.add(screen.getScStartDate().toString().split("T")[0]);
        }
        // 결과
        // [2024-12-11, 2024-12-12, 2024-12-13, 2024-12-14, 2024-12-15, 2024-12-16, 2024-12-17, 2024-12-18, 2024-12-19, 2024-12-20, 2024-12-21, 2024-12-22, 2024-12-23, 2024-12-24, 2024-12-25, 2024-12-26]

        SortedSet<String> sortSet = new TreeSet<>();
        for (String sort : sortedSet) {
            sortSet.add(sort.substring(0, 7));
        }
        // 결과
        // [2024-12]

        Map<String, String> map = new TreeMap<>();
        for (String title : sortSet) {
            List<String> list = new ArrayList<>();
            for (String day : sortedSet) {
                if (day.contains(title)) {
                    LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    list.add(day.split("-")[2] + "-" + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN).split("요일")[0]);
                }
            }
            map.put(title, list.toString().replace('[', ' ').replace(']', ' '));
        }
        // 결과
        // 2024-12 [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]

        // 결과 반환
        return map;
    }


    public Map<String, String> getWeekdaysByMoTitle(String moTitle) {
        // 화면의 시작 날짜들을 가져옴
        MovieVo[] screens = this.ticketMapper.selectAllMoviesByMoTitle(moTitle);

        // 고유 날짜를 저장할 Set
        SortedSet<String> sortedSet = new TreeSet<>();

        // 날짜 리스트를 돌면서 고유 날짜만 저장
        for (MovieVo screen : screens) {
            sortedSet.add(screen.getScStartDate().toString().split("T")[0]);
        }
        // 결과
        // [2024-12-11, 2024-12-12, 2024-12-13, 2024-12-14, 2024-12-15, 2024-12-16, 2024-12-17, 2024-12-18, 2024-12-19, 2024-12-20, 2024-12-21, 2024-12-22, 2024-12-23, 2024-12-24, 2024-12-25, 2024-12-26]

        SortedSet<String> sortSet = new TreeSet<>();
        for (String sort : sortedSet) {
            sortSet.add(sort.substring(0, 7));
        }
        // 결과
        // [2024-12]

        Map<String, String> map = new TreeMap<>();
        for (String title : sortSet) {
            List<String> list = new ArrayList<>();
            for (String day : sortedSet) {
                if (day.contains(title)) {
                    LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    list.add(day.split("-")[2] + "-" + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN).split("요일")[0]);
                }
            }
            map.put(title, list.toString().replace('[', ' ').replace(']', ' '));
        }
        // 결과
        // 2024-12 [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]

        // 결과 반환
        return map;
    }

    public Map<String, String> getWeekdaysByThName(String thName) {
        // 화면의 시작 날짜들을 가져옴
        MovieVo[] screens = this.ticketMapper.selectAllMoviesByThName(thName);

        // 고유 날짜를 저장할 Set
        SortedSet<String> sortedSet = new TreeSet<>();

        // 날짜 리스트를 돌면서 고유 날짜만 저장
        for (MovieVo screen : screens) {
            sortedSet.add(screen.getScStartDate().toString().split("T")[0]);
        }
        // 결과
        // [2024-12-11, 2024-12-12, 2024-12-13, 2024-12-14, 2024-12-15, 2024-12-16, 2024-12-17, 2024-12-18, 2024-12-19, 2024-12-20, 2024-12-21, 2024-12-22, 2024-12-23, 2024-12-24, 2024-12-25, 2024-12-26]

        SortedSet<String> sortSet = new TreeSet<>();
        for (String sort : sortedSet) {
            sortSet.add(sort.substring(0, 7));
        }
        // 결과
        // [2024-12]

        Map<String, String> map = new TreeMap<>();
        for (String title : sortSet) {
            List<String> list = new ArrayList<>();
            for (String day : sortedSet) {
                if (day.contains(title)) {
                    LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    list.add(day.split("-")[2] + "-" + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN).split("요일")[0]);
                }
            }
            map.put(title, list.toString().replace('[', ' ').replace(']', ' '));
        }
        // 결과
        // 2024-12 [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]

        // 결과 반환
        return map;
    }

    public Map<String, String> getWeekdays() {
        // 화면의 시작 날짜들을 가져옴
        ScreenEntity[] screens = this.ticketMapper.selectAllScreenDates();

        // 고유 날짜를 저장할 Set
        SortedSet<String> sortedSet = new TreeSet<>();

        // 날짜 리스트를 돌면서 고유 날짜만 저장
        for (ScreenEntity screen : screens) {
            sortedSet.add(screen.getScStartDate().toString().split("T")[0]);
        }
        // 결과
        // [2024-12-11, 2024-12-12, 2024-12-13, 2024-12-14, 2024-12-15, 2024-12-16, 2024-12-17, 2024-12-18, 2024-12-19, 2024-12-20, 2024-12-21, 2024-12-22, 2024-12-23, 2024-12-24, 2024-12-25, 2024-12-26]

        SortedSet<String> sortSet = new TreeSet<>();
        for (String sort : sortedSet) {
            sortSet.add(sort.substring(0, 7));
        }
        // 결과
        // [2024-12]

        Map<String, String> map = new TreeMap<>();
        for (String title : sortSet) {
            List<String> list = new ArrayList<>();
            for (String day : sortedSet) {
                if (day.contains(title)) {
                    LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    list.add(day.split("-")[2] + "-" + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN).split("요일")[0]);
                }
            }
            map.put(title, list.toString().replace('[', ' ').replace(']', ' '));
        }
        // 결과
        // 2024-12 [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27]

        // 결과 반환
        return map;
    }

    // region 크롤링을 위한 영화관 열거형
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
    // endregion

    // region 크롤링을 위한 영화관 타입 열거형
    @Getter
    public enum CinemaCode {
        NORMAL("2D"),
        IMAX("IMAX"),
        FOURDX("4DX"),
        SCREENX("SCREENX"),
        RECLINER("리클라이너"),
        CINE("CINE&FORET");

        private final String citName;

        CinemaCode(String citName) {
            this.citName = citName;
        }
    }
    // endregion

    // region 열거형 예시
//    public class EnumExample {
//        public static void main(String[] args) {
//            // enum을 for-each 문으로 반복
//            for (TheaterCode color : TheaterCode.values()) {
//                System.out.println(color.name() + " " + color.ordinal());
//            }
//        }
//    }
    // endregion

    // region 크롤링
    @Transactional
    public void Crawl(ScreenEntity screen) throws TransactionalException {
        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe"); // chromedriver.exe 경로 지정

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled"); // 자동화 브라우저 감지 비활성화
        options.addArguments("--headless"); // 브라우저 창을 띄우지 않고 실행 (옵션)

        // WebDriver 생성
        WebDriver driver = new ChromeDriver();

        try {
            // 오늘 날짜 가져오기

            for (TheaterCode theater : TheaterCode.values()) {
                int ciNum = 0;
                String dateUrl = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=" + theater.cgvCode;
                driver.get(dateUrl);

                // iframe 요소 찾기 및 전환
                WebElement iframe = driver.findElement(By.id("ifrm_movie_time_table"));
                driver.switchTo().frame(iframe);

                List<WebElement> dateElements = driver.findElements(By.cssSelector("#slider > .item-wrap.on > .item > li"));
                List<String> dates = new ArrayList<>();
                for (WebElement day : dateElements) {
                    // 영화 제목 추출
                    String movie = day.findElement(By.cssSelector("a")).getAttribute("href");
                    if (movie.isEmpty()) {
                        continue;
                    }
                    // URL에서 쿼리 파라미터 추출
                    URL url = new URL(movie);
                    String query = url.getQuery();

                    // 쿼리 파라미터가 없다면 건너뜁니다.
                    if (query == null || query.isEmpty()) {
                        continue;
                    }

                    // 쿼리 파라미터 분리
                    Map<String, String> queryParams = new HashMap<>();
                    String[] pairs = query.split("&");

                    // 각 파라미터에 대해 처리
                    for (String pair : pairs) {
                        String[] keyValue = pair.split("=");

                        // '='가 없거나 keyValue의 길이가 2가 아니라면 건너뜁니다.
                        if (keyValue.length == 2) {
                            queryParams.put(keyValue[0], keyValue[1]);
                        }
                    }

                    // 'date' 파라미터 값 추출
                    String date = queryParams.get("date");

                    // date 출력
                    if (date != null) {
                        dates.add(date);
                    }
                }
                System.out.println(dates);
                System.out.println(theater.cgvName);

                // 오늘을 기준으로 해당 영화관에 존재하는 날짜만 크롤링
                for (int i = 0; i < dates.toArray().length; i++) {
                    String date = dates.toArray()[i].toString(); // YYYYMMDD 형식의 날짜
                    System.out.println("상영일: " + date);

                    // URL에 날짜 파라미터 추가
                    String url = "http://www.cgv.co.kr/theaters/?areacode=11&theaterCode=" + theater.cgvCode + "&date=" + date;
                    // CGV 극장 URL 열기
                    driver.get(url);

                    // iframe 요소 찾기 및 전환
                    WebElement iframes = driver.findElement(By.id("ifrm_movie_time_table"));
                    driver.switchTo().frame(iframes);

                    // 영화 시간표 요소 가져오기
                    List<WebElement> movieElements = driver.findElements(By.cssSelector(".col-times"));

                    for (WebElement movieElement : movieElements) {
                        // 영화 제목 추출
                        String movieTitle = movieElement.findElement(By.cssSelector(".info-movie > a > strong")).getText().trim();
                        MovieEntity movieNum = this.ticketMapper.selectMovieNumByMovieTitle(movieTitle);
                        if (movieNum == null) {
                            break;
                        }
                        screen.setMoNum(movieNum.getMoNum());

                        // 상영 시간표 추출
                        List<WebElement> timeTables = movieElement.findElements(By.cssSelector(".type-hall"));
                        StringBuilder timeTable = new StringBuilder();
                        for (WebElement table : timeTables) {
                            List<WebElement> cinemas = table.findElements(By.cssSelector(".info-hall > ul > li:nth-child(2)"));
                            for (WebElement cinema : cinemas) {
                                String result = "";
                                for (CinemaCode code : CinemaCode.values()) {
                                    if (cinema.getText().trim().contains("4DX관")) {
                                        result = "4DX";
                                        CinemaEntity cinemaTypeNum = this.ticketMapper.selectCinemaNumByCinemaType(result, theater.cgvName);
                                        screen.setCiNum(cinemaTypeNum.getCiNum());
                                        ciNum = cinemaTypeNum.getCiNum();
                                        break;
                                    }
                                    if (cinema.getText().trim().contains("씨네앤포레")) {
                                        result = "CINE&FORET";
                                        CinemaEntity cinemaTypeNum = this.ticketMapper.selectCinemaNumByCinemaType(result, theater.cgvName);
                                        screen.setCiNum(cinemaTypeNum.getCiNum());
                                        ciNum = cinemaTypeNum.getCiNum();
                                        break;
                                    }
                                    if (cinema.getText().contains("[CGV아트하우스]") ||
                                            cinema.getText().contains("[영남이공대학교]") ||
                                            cinema.getText().contains("[아트기획전관]")) {
                                        result = cinema.getText();
                                        CinemaEntity artCinema = this.ticketMapper.selectCinemaNumByCinemaTitle(result.substring(0, 2), theater.cgvName);
                                        screen.setCiNum(artCinema.getCiNum());
                                        ciNum = artCinema.getCiNum();
                                        break;
                                    }
                                    if (cinema.getText().contains("비상설")) {
                                        continue;
                                    }
                                    if (code.citName.equals(cinema.getText())) {
                                        result = cinema.getText().trim();
                                        CinemaEntity cinemaTypeNum = this.ticketMapper.selectCinemaNumByCinemaType(result, theater.cgvName);
                                        screen.setCiNum(cinemaTypeNum.getCiNum());
                                        ciNum = cinemaTypeNum.getCiNum();
                                        break;
                                    } else {
                                        screen.setCiNum(0);
                                    }
                                }
                                if (screen.getCiNum() == 0) { // 조건에 맞는 값을 찾지 못한 경우 처리
                                    if (cinema.getText() != null && cinema.getText().length() >= 3) {
                                        result = cinema.getText().trim();
                                        CinemaEntity cinemaNum = this.ticketMapper.selectCinemaNumByCinemaTitle(result.substring(0, 3), theater.cgvName);
                                        screen.setCiNum(cinemaNum.getCiNum());
                                        ciNum = cinemaNum.getCiNum();
                                    } else if (cinema.getText() != null && cinema.getText().length() >= 2) {
                                        result = cinema.getText().trim();
                                        CinemaEntity cinemaNum = this.ticketMapper.selectCinemaNumByCinemaTitle(result.substring(0, 2), theater.cgvName);
                                        screen.setCiNum(cinemaNum.getCiNum());
                                        ciNum = cinemaNum.getCiNum();
                                    }
                                }
                                timeTable.append("상영관: ").append(result).append("\n");
                                List<WebElement> timeElements = table.findElements(By.cssSelector(".info-timetable > ul > li > a > em"));
                                for (WebElement element : timeElements) {
                                    String updatedText;
                                    timeTable.append("상영 시간: ").append(element.getText()).append("\n");
                                    if (Integer.parseInt(element.getText().substring(0,2)) >= 24) {
                                        date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).plusDays(1).toString().replaceAll("-", "");
                                        updatedText = element.getText().replace(element.getText().substring(0, 2), "00");
                                    } else {
                                        updatedText = element.getText();
                                    }
                                    String dateTimeString = date + "T" + updatedText;
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm");
                                    screen.setScStartDate(LocalDateTime.parse(dateTimeString, formatter));
                                    ScreenEntity[] screens = this.ticketMapper.selectDuplicateScreen(LocalDateTime.parse(dateTimeString, formatter), this.ticketMapper.selectMovieNumByMovieTitle(movieTitle).getMoNum(), ciNum);
                                    if (screens.length < 1) {
                                        this.ticketMapper.insertScreen(screen);
                                    }
                                }
                            }
                        }

                        // 출력
                        System.out.println("------------");
                        System.out.println("영화: " + movieTitle);
                        System.out.println(timeTable.toString().trim());
                    }
                    System.out.println("------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 브라우저 닫기
            driver.quit();
        }
    }
    // endregion
}
