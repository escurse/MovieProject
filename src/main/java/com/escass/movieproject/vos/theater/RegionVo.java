package com.escass.movieproject.vos.theater;

import com.escass.movieproject.entities.theater.RegionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionVo extends RegionEntity {
    private int theaterCount;
}
