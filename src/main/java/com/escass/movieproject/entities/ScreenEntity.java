package com.escass.movieproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"num"})
public class ScreenEntity {
    private int num;
}
