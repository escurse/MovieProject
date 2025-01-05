package com.escass.movieproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScreenInfoDTO {
    private int scNum;
    private String ciName;
    private LocalDateTime scStartDate;
    private String mImgUrl;
}
