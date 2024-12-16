package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.CinemaEntity;
import com.escass.movieproject.entities.MovieEntity;
import com.escass.movieproject.entities.ScreenEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.vos.MovieVo;
import com.escass.movieproject.vos.RegionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface TicketMapper {
    int insertScreen(ScreenEntity screen);

    ScreenEntity[] selectAllScreenDates();

    RegionVo[] selectRegionAndTheaterCount();

    MovieVo[] selectAllMoviesByMoTitle(@Param(value = "moTitle") String moTitle);

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
