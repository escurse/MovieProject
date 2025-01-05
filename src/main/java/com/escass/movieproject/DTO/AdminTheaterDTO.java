package com.escass.movieproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminTheaterDTO {
    private int scNum;
    private String mImgUrl;
    private String thName;
    private String ciName;
    private String moTitle;
    private LocalDateTime scStartDate;
}
