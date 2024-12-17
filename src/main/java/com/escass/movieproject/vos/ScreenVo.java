package com.escass.movieproject.vos;

import com.escass.movieproject.entities.ScreenEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenVo extends ScreenEntity {
    private String ciName;
    private int thNum;
    private int citNum;
    private String citName;
    private int seatCount;
}
