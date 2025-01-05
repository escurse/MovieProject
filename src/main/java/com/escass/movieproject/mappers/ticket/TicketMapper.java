package com.escass.movieproject.mappers.ticket;

import com.escass.movieproject.entities.movie.MovieEntity;
import com.escass.movieproject.entities.theater.CinemaEntity;
import com.escass.movieproject.entities.theater.ScreenEntity;
import com.escass.movieproject.entities.theater.TheaterEntity;
import com.escass.movieproject.vos.theater.MovieVo;
import com.escass.movieproject.vos.theater.RegionVo;
import com.escass.movieproject.vos.theater.ScreenVo;
import com.escass.movieproject.vos.ticket.CinemaTypeVo;
import com.escass.movieproject.vos.ticket.SeatVo;
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

    MovieVo[] selectAllMoviesByMoTitleAndRegion(@Param(value = "moTitle") String moTitle,
                                                @Param(value = "region") String region);

    MovieVo[] selectAllMoviesByThName(@Param(value = "thName") String thName);

    MovieVo[] selectAllMoviesByScStartDate(@Param(value = "scStartDate") String scStartDate);

    MovieVo[] selectAllMoviesByScStartDateAndRegion(@Param(value = "scStartDate") String scStartDate,
                                                    @Param(value = "region") String region);

    MovieVo[] selectAllMoviesByMoTitleAndThName(@Param(value = "moTitle") String moTitle,
                                                @Param(value = "thName") String thName);

    MovieVo[] selectAllMoviesByMoTitleAndScStartDate(@Param(value = "moTitle") String moTitle,
                                                     @Param(value = "scStartDate") String scStartDate);

    MovieVo[] selectAllMoviesByMoTitleAndScStartDateAndRegion(@Param(value = "moTitle") String moTitle,
                                                     @Param(value = "scStartDate") String scStartDate,
                                                              @Param(value = "region") String region);

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

//    ---------------------------------------

    SeatVo[] selectSeatByReservationSeNum(
            @Param("ciName") String ciName,
            @Param("thName") String thName,
            @Param("moTitle") String moTitle,
            @Param("scStartDate") LocalDateTime scStartDate);

    CinemaTypeVo[] selectSeatByCitPrice(
            @Param("ciName") String ciName,
            @Param("thName") String thName,
            @Param("moTitle") String moTitle,
            @Param("scStartDate") LocalDateTime scStartDate);

    Integer selectScNumByScName(@Param("moTitle") String moTitle,
                                @Param("ciName") String ciName,
                                @Param("thName") String thName,
                                @Param("scStartDate") LocalDateTime scStartDate);

}
