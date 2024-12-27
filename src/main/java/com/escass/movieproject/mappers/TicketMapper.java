package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.CinemaEntity;
import com.escass.movieproject.entities.MovieEntity;
import com.escass.movieproject.entities.ScreenEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.vos.MovieVo;
import com.escass.movieproject.vos.RegionVo;
import com.escass.movieproject.vos.ScreenVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface TicketMapper {
    int insertScreen(ScreenEntity screen);

    TheaterEntity selectTheater(@Param("thName") String thName);

    ScreenVo[] selectScreenDatesByMovieAndTheaterAndDate(@Param("movie") int moNum,
                                                         @Param("theater") int thNum,
                                                         @Param("date") String scStartDate);

    MovieVo[] selectShowTimesByMoTitle(@Param("movie") String movie);

    ScreenEntity[] selectAllScreenDates();

    RegionVo[] selectRegionAndTheaterCount();

    MovieVo[] selectAllMoviesByMoTitle(@Param(value = "moTitle") String moTitle);

    MovieVo[] selectAllMoviesByThName(@Param(value = "thName") String thName);

    MovieVo[] selectAllMoviesByScStartDate(@Param(value = "scStartDate") String scStartDate);

    MovieVo[] selectAllMoviesByMoTitleAndThName(@Param(value = "moTitle") String moTitle,
                                                     @Param(value = "thName") String thName);

    MovieVo[] selectAllMoviesByMoTitleAndScStartDate(@Param(value = "moTitle") String moTitle,
                                                     @Param(value = "scStartDate") String scStartDate);

    MovieVo[] selectAllMoviesByThNameAndScStartDate(@Param(value = "thName") String thName,
                                                     @Param(value = "scStartDate") String scStartDate);

    MovieVo[] selectAllMoviesByRating();

    MovieVo[] selectAllMoviesByKorea();

    MovieEntity selectMovieNumByMovieTitle(@Param(value = "moTitle") String moTitle);

    CinemaEntity selectCinemaNumByCinemaTitle(@Param(value = "ciName") String ciName,
                                              @Param(value = "thName") String thName);

    CinemaEntity selectCinemaNumByCinemaType(@Param(value = "citName") String citName,
                                             @Param(value = "thName") String thName);

    ScreenEntity[] selectDuplicateScreen(@Param(value = "startDate") LocalDateTime startDate,
                                         @Param(value = "moNum") int moNum,
                                         @Param(value = "ciNum") int ciNum);
}
