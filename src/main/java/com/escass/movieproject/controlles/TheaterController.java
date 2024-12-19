package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.results.Result;
import com.escass.movieproject.services.TheaterService;
import com.escass.movieproject.vos.TheaterVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
@RequestMapping(value = "/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "theater", required = false) String theater) {
        ModelAndView modelAndView = new ModelAndView();
        RegionEntity[] regions = this.theaterService.findRegionAll();
        TheaterVo[] theaterVos = this.theaterService.selectAllTheaters("CGV대구");
        Map<Set<String>, List<Set<String>>> theaterMap = new HashMap<>();
        Set<String> keys = new LinkedHashSet<>();
        List<Set<String>> values = new ArrayList<>();
        Set<String> types = new LinkedHashSet<>();
        SortedSet<String> times = new TreeSet<>();
        for (TheaterVo theaterVo : theaterVos) {
            keys.add(theaterVo.getThName());
            keys.add(theaterVo.getThAddr().split("\n")[0]);
            keys.add(theaterVo.getThAddr().split("\n")[1]);
            keys.add(theaterVo.getThImg());
            keys.add(String.valueOf(theaterVo.getSeatCount()));
            keys.add(String.valueOf(theaterVo.getCinemaCount()));
            types.add(theaterVo.getCitName());
            times.add(String.valueOf(theaterVo.getScStartDate()).split("T")[0]);
        }
        values.add(types);
        values.add(times);
        theaterMap.computeIfAbsent(keys, k -> new ArrayList<>()).addAll(values);
        System.out.println(theaterMap);
//        Map<String, List<String>> maps = this.theaterService.Crawl();
        modelAndView.addObject("regions", regions);
        modelAndView.addObject("theaterVos", theaterVos);
//        modelAndView.addObject("maps", maps);
        modelAndView.setViewName("theater/index");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getIndexByRegion(@RequestParam(value = "region", required = false) String region,
                                   @RequestParam(value = "theater", required = false) String theater) {
        JSONObject response = new JSONObject();
        TheaterEntity[] theaters = this.theaterService.getTheatersByRegion(region);
        Pair<Integer, Integer> counts = this.theaterService.getTheaterSeatCount(theater);
        response.put(Result.NAME, theaters);
        response.put(Result.RESULT, counts);
        return response.toString();
    }
}
