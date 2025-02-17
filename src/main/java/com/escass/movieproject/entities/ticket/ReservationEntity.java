package com.escass.movieproject.entities.ticket;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(of = {"reNum"})
public class ReservationEntity {
    private int reNum;
    private int scNum;
    private int seNum;
    private int paNum;
}
