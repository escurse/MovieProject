package com.escass.movieproject.entities.movie;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"mImgNum"})
public class MovieImageEntity {
    private int mImgNum;
    private String mImgUrl;
    private int MoNum;
}
