package com.escass.movieproject.vos.theater;

import com.escass.movieproject.entities.theater.TheaterEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TheaterVo extends TheaterEntity {
    private String citName;
    private LocalDateTime scStartDate;
    private int cinemaCount;
    private int seatCount;
}
