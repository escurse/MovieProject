package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TheaterMapper {
    RegionEntity[] getRegionAll();

    TheaterEntity[] getTheaterAll();

    TheaterEntity[] getTheatersAll();

    TheaterEntity[] getTheatersByRegion(@Param(value = "region") String region);
}
