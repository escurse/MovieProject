package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TheaterMapper {
    RegionEntity[] getRegionAll();

    TheaterEntity[] getTheaterAll();

    TheaterEntity[] getTheatersAll();
}
