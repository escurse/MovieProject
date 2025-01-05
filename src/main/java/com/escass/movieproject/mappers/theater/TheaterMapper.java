package com.escass.movieproject.mappers.theater;

import com.escass.movieproject.entities.theater.RegionEntity;
import com.escass.movieproject.entities.theater.TheaterEntity;
import com.escass.movieproject.vos.theater.ScreenVo;
import com.escass.movieproject.vos.theater.TheaterVo;
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

    ScreenVo[] selectAllScreensByRegion(@Param(value = "date") String date,
                                        @Param(value = "region") String region,
                                        @Param(value = "movie") String movie);

    ScreenVo[] selectAllScreensByCinemaType(@Param(value = "date") String date,
                                            @Param(value = "region") String region,
                                            @Param(value = "movie") String movie,
                                            @Param(value = "cinema") String cinema);
}
