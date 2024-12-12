package com.escass.movieproject.vos;

import com.escass.movieproject.entities.RegionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionVo extends RegionEntity {
    private int theaterCount;
}
