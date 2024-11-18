package com.escass.movieproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"citNum"})
public class CinemaTypeEntity {
    private int citNum;
    private String citName;
}
