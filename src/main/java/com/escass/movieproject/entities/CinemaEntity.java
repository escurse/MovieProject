package com.escass.movieproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"ciNum"})
public class CinemaEntity {
    private int ciNum;
    private String ciName;
    private String ciType;
    private int ciMaxSeat;
    private int thNum;
}
