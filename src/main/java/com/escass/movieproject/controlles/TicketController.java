package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.ScreenEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.services.TheaterService;
import com.escass.movieproject.services.TicketService;
import com.escass.movieproject.vos.MovieVo;
import com.escass.movieproject.vos.RegionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TheaterService theaterService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "region", required = false) String region,
                                 @RequestParam(value = "moTitle", required = false) String moTitle) {
        ModelAndView modelAndView = new ModelAndView();
        MovieVo[] movies = this.ticketService.selectAllMovies();
        RegionVo[] regions = this.ticketService.selectRegionAndTheaterCount();
        TheaterEntity[] theaters = this.theaterService.getTheatersByRegion(region);
        Map<String, String> maps = this.ticketService.getWeekdays();
        modelAndView.addObject("movies", movies);
        modelAndView.addObject("regions", regions);
        modelAndView.addObject("theaters", theaters);
        modelAndView.addObject("maps", maps);
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
    public ModelAndView Crawl(ScreenEntity screen) {
        ModelAndView modelAndView = new ModelAndView();
        this.ticketService.Crawl(screen);
        modelAndView.setViewName("ticket/crawling");
        return modelAndView;
    }
}
