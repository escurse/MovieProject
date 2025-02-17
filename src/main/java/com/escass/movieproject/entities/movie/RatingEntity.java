package com.escass.movieproject.entities.movie;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"raNum"})
public class RatingEntity {
    private int raNum;
    private String raGrade;
}
