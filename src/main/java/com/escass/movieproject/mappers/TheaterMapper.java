package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.vos.TheaterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TheaterMapper {
    RegionEntity[] getRegionAll();

    TheaterEntity[] getTheatersByRegion(@Param(value = "region") String region);

    TheaterVo[] selectAllTheaters(@Param(value = "theater") String theater);

    int getCinemaCountByTheater(@Param(value = "theater") String theater);

    int getSeatCountByCinema(@Param(value = "theater") String theater);
}
