package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.services.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        RegionEntity[] region = this.theaterService.findRegionAll();
        TheaterEntity[] theater = this.theaterService.findTheaterAll();
        String[] a = new String[theater.length];
        for (TheaterEntity theaters : theater) {
            a = theaters.getThAddr().split("\n");
        }
        modelAndView.addObject("regions", region);
        modelAndView.addObject("theaters", theater);
        modelAndView.addObject("a", a);
        modelAndView.setViewName("theater/index");
        return modelAndView;
    }
}
