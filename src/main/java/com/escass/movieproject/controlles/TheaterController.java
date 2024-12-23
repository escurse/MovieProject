package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.services.TheaterService;
import com.escass.movieproject.vos.TheaterVo;
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
@RequestMapping(value = "/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "theater", required = false) String theater,
            @RequestParam(value = "date", required = false) String date) {
        ModelAndView modelAndView = new ModelAndView();
        RegionEntity[] regions = this.theaterService.findRegionAll();
        TheaterEntity[] theaters = this.theaterService.getTheatersByRegion(region);
        if (theater != null) {
            TheaterVo[] theaterVos = this.theaterService.selectAllTheaters(theater);
            Map<String, String> maps = this.theaterService.getWeekdays(theater);
            Set<String> keys = new LinkedHashSet<>();
            List<Object[]> values = new ArrayList<>();
            Set<String> types = new LinkedHashSet<>();
            for (TheaterVo theaterVo : theaterVos) {
                keys.add(theaterVo.getThName());
                keys.add(theaterVo.getThAddr().split("\n")[0]);
                keys.add(theaterVo.getThAddr().split("\n")[1]);
                keys.add(theaterVo.getThImg());
                keys.add(String.valueOf(theaterVo.getSeatCount()));
                keys.add(String.valueOf(theaterVo.getCinemaCount()));
                keys.add(theaterVo.getThParking());
                if (theaterVo.getCitName().equals("4DX")) {
                    theaterVo.setCitName("DX");
                }
                types.add(theaterVo.getCitName());
            }
            values.add(new Object[]{keys, types, maps});
            modelAndView.addObject("theaterVos", values);
        }
        if (date != null && theater != null) {
            Map<Set<String>, Map<Set<String>, Set<String>>> screenVos = this.theaterService.selectAllScreens(date, theater);
            modelAndView.addObject("screenVos", screenVos);
        }
        modelAndView.addObject("regions", regions);
        modelAndView.addObject("theaters", theaters);
        modelAndView.setViewName("theater/index");
        return modelAndView;
    }
}
