package com.escass.movieproject.mappers;

import com.escass.movieproject.entities.RegionEntity;
import com.escass.movieproject.entities.TheaterEntity;
import com.escass.movieproject.vos.ScreenVo;
import com.escass.movieproject.vos.TheaterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TheaterMapper {
    RegionEntity[] getRegionAll();

    TheaterEntity[] getTheatersByRegion(@Param(value = "region") String region);

    TheaterVo[] selectAllTheaters(@Param(value = "theater") String theater);

    TheaterVo[] selectAllTheatersByRegion(@Param(value = "region") String region,
                                          @Param(value = "movie") String movie);

    ScreenVo[] selectAllScreens(@Param(value = "date") String date,
                                @Param(value = "theater") String theater);

    ScreenVo[] selectAllScreensByCinemaType(@Param(value = "date") String date,
                                            @Param(value = "region") String region,
                                            @Param(value = "movie") String movie,
                                            @Param(value = "cinema") String cinema);
}
