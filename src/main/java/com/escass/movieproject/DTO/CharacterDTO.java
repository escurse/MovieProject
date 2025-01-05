package com.escass.movieproject.DTO;

import com.escass.movieproject.entities.movie.CharactorEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO extends CharactorEntity {
    private Integer CImgNum;
    private String CImgUrl;
}
