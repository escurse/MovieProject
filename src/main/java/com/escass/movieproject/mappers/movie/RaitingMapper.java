package com.escass.movieproject.mappers.movie;

import com.escass.movieproject.entities.movie.RatingEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RaitingMapper {
    int insertMovieRaiting(RatingEntity ratingEntity);
    Integer selectRaitingIdByName(String RaitingName);
}
