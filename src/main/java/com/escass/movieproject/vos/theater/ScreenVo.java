package com.escass.movieproject.vos.theater;

import com.escass.movieproject.entities.theater.ScreenEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenVo extends ScreenEntity {
    private String ciName;
    private int thNum;
    private String citName;
    private int seatCount;
    private String moTitle;
    private String moDate;
    private int moTime;
    private String geName;
    private int citNum;
    private String raGrade;
    private int usedSeatCount;
    private String thName;
}
