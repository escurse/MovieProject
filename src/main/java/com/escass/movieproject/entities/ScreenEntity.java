package com.escass.movieproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"scNum"})
public class ScreenEntity {
    private int scNum;
    private String scStartDate;
    private int moNum;
    private int ciNum;
}
