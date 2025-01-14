package com.escass.movieproject.controlles;

import com.escass.movieproject.DTO.Movie_ImageDTO;
import com.escass.movieproject.DTO.ResultDto;
import com.escass.movieproject.entities.user.UserEntity;
import com.escass.movieproject.results.Result;
import com.escass.movieproject.services.movie.MovieService;
import com.escass.movieproject.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    public final MovieService movieService;
    public final UserService userService;

    @Autowired
    public HomeController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    //region 메인화면
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        List<Movie_ImageDTO> currentMovies = this.movieService.selectCaraouselCurrnetMovieList();
        List<Movie_ImageDTO> upComingMovies = this.movieService.selectCarouselUpcomingMovieList();
        modelAndView.addObject("currentMovies", currentMovies);
        modelAndView.addObject("upComingMovies", upComingMovies);
        return modelAndView;
    }
    // endregion

    // region 로그아웃

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) throws URISyntaxException, IOException, InterruptedException {
        ResultDto<Result, UserEntity> result = this.userService.socialLogout((UserEntity) session.getAttribute(UserEntity.NAME_SINGULAR));
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    // endregion
}
