package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.ScreenEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.entities.user.UserEntity;
import com.escass.movieproject.services.TheaterService;
import com.escass.movieproject.services.TicketService;
import com.escass.movieproject.vos.MovieVo;
import com.escass.movieproject.vos.RegionVo;
import com.escass.movieproject.vos.ScreenVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
@RequestMapping(value = "/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TheaterService theaterService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "region", required = false) String region,
                                 @RequestParam(value = "moTitle", required = false) String moTitle,
                                 @RequestParam(value = "thName", required = false) String thName,
                                 @RequestParam(value = "scStartDate", required = false) String scStartDate,
                                 HttpSession session, UserEntity user) {
        ModelAndView modelAndView = new ModelAndView();
        MovieVo[] movies = this.ticketService.selectAllMoviesByRating();
        RegionVo[] regions = this.ticketService.selectRegionAndTheaterCount();
        TheaterEntity[] theaters = this.theaterService.getTheatersByRegion(region);
        Map<String, String> maps = this.ticketService.getWeekdays();
        if (moTitle != null && thName == null && scStartDate == null) {
            MovieVo[] movieVos = this.ticketService.selectAllMovies(moTitle);
            List<Object[]> vos = new ArrayList<>();
            Map<String, String> moMaps = this.ticketService.getWeekdaysByMoTitle(moTitle);
            Set<String> keys = new LinkedHashSet<>();
            SortedSet<String> thKeys = new TreeSet<>();
            for (MovieVo vo : movieVos) {
                keys.add(vo.getMoTitle());
                // 영화제목
                keys.add(vo.getMImgUrl());
                // 포스터 이미지
                keys.add(vo.getRaGrade());
                // 등급
                keys.add(String.valueOf(vo.getTheaterCount()));
                // 영화관 갯수
                keys.add(vo.getRegName());
                // 지역 이름
                thKeys.add(vo.getThName());
            }
            vos.add(new Object[]{keys, thKeys, moMaps});
            modelAndView.addObject("movieVos", vos);
        }
        if (moTitle == null && thName != null && scStartDate == null) {
            MovieVo[] movieVos = this.ticketService.selectAllMoviesByThName(thName);
            List<Object[]> vos = new ArrayList<>();
            Map<String, String> moMaps = this.ticketService.getWeekdaysByThName(thName);
            Map<String, Boolean> map = new HashMap<>();

            for (MovieVo vo : movieVos) {
                switch (vo.getRaGrade()) {
                    case "청소년관람불가" -> vo.setRaGrade("nineteen");
                    case "15세이상관람가" -> vo.setRaGrade("fifteen");
                    case "12세이상관람가" -> vo.setRaGrade("twelve");
                    case "전체관람가" -> vo.setRaGrade("all");
                    case "미정" -> vo.setRaGrade("none");
                }
                // 영화 제목과 등급을 결합하여 key로 사용
                String key = vo.getMoTitle() + "&&" + vo.getRaGrade();

                // Map에 저장하여 중복을 제거
                map.put(key, true);
            }
            vos.add(new Object[]{map, moMaps});
            modelAndView.addObject("theaterVos", vos);
        }
        if (moTitle == null && thName == null && scStartDate != null) {
            MovieVo[] movieVos = this.ticketService.selectAllMoviesByscStartDate(scStartDate);
            List<Object[]> vos = new ArrayList<>();
            SortedSet<String> thKeys = new TreeSet<>();
            Map<String, Boolean> map = new HashMap<>();
            for (MovieVo vo : movieVos) {
                switch (vo.getRaGrade()) {
                    case "청소년관람불가" -> vo.setRaGrade("nineteen");
                    case "15세이상관람가" -> vo.setRaGrade("fifteen");
                    case "12세이상관람가" -> vo.setRaGrade("twelve");
                    case "전체관람가" -> vo.setRaGrade("all");
                    case "미정" -> vo.setRaGrade("none");
                }
                // 영화 제목과 등급을 결합하여 key로 사용
                String key = vo.getMoTitle() + "&&" + vo.getRaGrade();

                // Map에 저장하여 중복을 제거
                map.put(key, true);
                thKeys.add(vo.getThName());
            }
            vos.add(new Object[]{map, thKeys});
            modelAndView.addObject("dateVos", vos);
        }
        if (moTitle != null && thName != null && scStartDate == null) {
            List<Object[]> vos = new ArrayList<>();
            Map<String, String> moMaps = this.ticketService.getWeekdaysByMoTitleAndThName(moTitle, thName);
            vos.add(new Object[]{moMaps});
            modelAndView.addObject("moThVos", vos);
        }
        if (moTitle != null && thName == null && scStartDate != null) {
            MovieVo[] movieVos = this.ticketService.selectAllMoviesBymoTitleAndscStartDate(moTitle, scStartDate);
            List<Object[]> vos = new ArrayList<>();
            SortedSet<String> thKeys = new TreeSet<>();
            for (MovieVo vo : movieVos) {
                thKeys.add(vo.getThName());
            }
            vos.add(new Object[]{thKeys});
            modelAndView.addObject("moScVos", vos);
        }
        if (moTitle == null && thName != null && scStartDate != null) {
            MovieVo[] movieVos = this.ticketService.selectAllMoviesByThNameAndScStartDate(thName, scStartDate);
            List<Object[]> vos = new ArrayList<>();
            Set<String> keys = new LinkedHashSet<>();
            Map<String, Boolean> map = new HashMap<>();
            for (MovieVo vo : movieVos) {
                switch (vo.getRaGrade()) {
                    case "청소년관람불가" -> vo.setRaGrade("nineteen");
                    case "15세이상관람가" -> vo.setRaGrade("fifteen");
                    case "12세이상관람가" -> vo.setRaGrade("twelve");
                    case "전체관람가" -> vo.setRaGrade("all");
                    case "미정" -> vo.setRaGrade("none");
                }
                String key = vo.getMoTitle() + "&&" + vo.getRaGrade();
                map.put(key, true);
            }
            vos.add(new Object[]{keys, map});
            modelAndView.addObject("thScVos", vos);
        }
        if (moTitle != null && thName != null && scStartDate != null) {
            MovieVo[] movieVos = this.ticketService.selectAllMovies(moTitle);
            ScreenVo[] screens = this.ticketService.selectScreenDatesByMovieAndTheaterAndDate(moTitle, thName, scStartDate);
            Map<List<String>, List<List<String>>> times = new HashMap<>();
            List<Object[]> vos = new ArrayList<>();
            Set<String> MoKeys = new LinkedHashSet<>();
            for (MovieVo vo : movieVos) {
                MoKeys.add(vo.getMoTitle());
                // 영화제목
                MoKeys.add(vo.getMImgUrl());
                // 포스터 이미지
                MoKeys.add(vo.getRaGrade());
                // 등급
                MoKeys.add(String.valueOf(vo.getTheaterCount()));
                // 영화관 갯수
                MoKeys.add(vo.getRegName());
                // 지역 이름
            }
            vos.add(new Object[]{MoKeys});
            for (ScreenVo screen : screens) {
                List<String> keys = new ArrayList<>();
                List<String> contents = new ArrayList<>();
                keys.add(screen.getCitName());
                // 2D
                keys.add(screen.getCiName());
                // 5관
                keys.add(String.valueOf(screen.getSeatCount()));
                // 40석
                contents.add(String.valueOf(screen.getScStartDate()));
                contents.add(String.valueOf(screen.getSeatCount()));
                times.computeIfAbsent(keys, k -> new ArrayList<>()).add(contents);
            }
            modelAndView.addObject("times", times);
            modelAndView.addObject("Vos", vos);
        }
        modelAndView.addObject("movies", movies);
        modelAndView.addObject("regions", regions);
        modelAndView.addObject("theaters", theaters);
        modelAndView.addObject("maps", maps);
        modelAndView.addObject("session", session);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("ticket/index");
        return modelAndView;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getMovies() {
        ModelAndView modelAndView = new ModelAndView();
        MovieVo[] movies = this.ticketService.selectAllMoviesByKorea();
        modelAndView.addObject("movies", movies);
        modelAndView.setViewName("ticket/index");
        return modelAndView;
    }

    @RequestMapping(value = "/crawling", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView Crawling(ScreenEntity screen) {
        ModelAndView modelAndView = new ModelAndView();
        this.ticketService.Crawl(screen);
        modelAndView.setViewName("ticket/crawling");
        return modelAndView;
    }
}
