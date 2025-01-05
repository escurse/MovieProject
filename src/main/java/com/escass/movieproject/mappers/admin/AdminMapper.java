package com.escass.movieproject.mappers.admin;

import com.escass.movieproject.DTO.AdminMovieDTO;
import com.escass.movieproject.DTO.AdminTheaterDTO;
import com.escass.movieproject.entities.movie.MovieEntity;
import com.escass.movieproject.entities.theater.ScreenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    // 영화
    MovieEntity[] selectAllMovie();

    int selectArticleCountByMovieName();

    int searchArticleCountByMovieName(@Param("filter") String filter,
                                      @Param("keyword") String keyword);

    AdminMovieDTO[] selectAllDTO();

    AdminMovieDTO[] selectArticleByMovieName(
            @Param("limitCount") int limitCount,
            @Param("offsetCount") int offsetCount);

    AdminMovieDTO[] searchArticleByMovieName(
            @Param("limitCount") int limitCount,
            @Param("offsetCount") int offsetCount,
            @Param("filter") String filter,
            @Param("keyword") String keyword);


    // 상영관
    int updateTheater(ScreenEntity screen);

    int selectArticleCountByTheater();

    int searchArticleCountByTheater(@Param("screenFilter") String screenFilter,
                                    @Param("screenKeyword") String screenKeyword);

    AdminTheaterDTO[] selectAllDTOByTheaters(@Param("limitCount") int limitCount,
                                             @Param("offsetCount") int offsetCount);

    AdminTheaterDTO[] searchAllDTOByTheaters(@Param("limitCount") int limitCount,
                                             @Param("offsetCount") int offsetCount,
                                             @Param("screenFilter") String screenFilter,
                                             @Param("screenKeyword") String screenKeyword);

    ScreenEntity selectScreenByScNum(@Param("scNum") int scNum);
}
