package com.escass.movieproject.DTO;

import com.escass.movieproject.entities.movie.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Movie_ImageDTO extends MovieEntity {
    private Integer moNum;
    private String moTitle;
    private String moDate;
    private int moTime;
    private String moPlot;
    private Float moBookingRate;
    private LocalDate moEnding;
    private String m_img_url;

}
