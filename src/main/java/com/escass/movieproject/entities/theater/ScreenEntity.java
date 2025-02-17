package com.escass.movieproject.entities.theater;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"scNum"})
public class ScreenEntity {
    private int scNum;
    private LocalDateTime scStartDate;
    private boolean isDeleted;
    private int moNum;
    private int ciNum;
}
