package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.CinemaEntity;
import com.escass.movieproject.entities.MovieEntity;
import com.escass.movieproject.entities.ScreenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TicketMapper {
    int insertScreen(ScreenEntity screen);

    MovieEntity selectMovieNumByMovieTitle(@Param(value = "moTitle") String moTitle);

    CinemaEntity selectCinemaNumByCinemaTitle(@Param(value = "ciName") String ciName,
                                              @Param(value = "thName") String thName);

    CinemaEntity selectCinemaNumByCinemaType(@Param(value = "citName") String citName,
                                             @Param(value = "thName") String thName);
}
