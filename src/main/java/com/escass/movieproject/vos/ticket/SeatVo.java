package com.escass.movieproject.vos.ticket;

import com.escass.movieproject.entities.ticket.SeatEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatVo extends SeatEntity {
    private String scNum;
}
