package com.escass.movieproject.entities.theater;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"thNum"})
public class TheaterEntity {
    private int thNum;
    private String thName;
    private String thAddr;
    private String thImg;
    private String thParking;
    private int regNum;
}
