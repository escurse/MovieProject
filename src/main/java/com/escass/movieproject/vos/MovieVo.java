package com.escass.movieproject.vos;

import com.escass.movieproject.entities.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieVo extends MovieEntity {
    private String raGrade;
    private String mImgUrl;
    private LocalDateTime scStartDate;
    private String thName;
    private int theaterCount;
    private String regName;
}
