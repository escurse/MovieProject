package com.escass.movieproject.vos;

import com.escass.movieproject.entities.MovieEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVo extends MovieEntity {
    private String raGrade;
    private String mImgUrl;
}
