package com.escass.movieproject.vos.ticket;

import com.escass.movieproject.entities.theater.CinemaTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaTypeVo extends CinemaTypeEntity {
    private int moTime;
    private String mImgUrl;

}
