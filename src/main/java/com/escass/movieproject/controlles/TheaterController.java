package com.escass.movieproject.controlles;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.results.Result;
import com.escass.movieproject.services.TheaterService;
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

@Slf4j
@Controller
@RequestMapping(value = "/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

//    @Scheduled(cron = "0 0 0 * * *")
//    public void doCrawl() {
//         상영 일정을 크롤링 한다.
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        RegionEntity[] regions = this.theaterService.findRegionAll();
        modelAndView.addObject("regions", regions);
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
