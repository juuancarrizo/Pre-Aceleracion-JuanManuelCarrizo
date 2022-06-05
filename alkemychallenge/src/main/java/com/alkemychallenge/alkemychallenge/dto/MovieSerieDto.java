package com.alkemychallenge.alkemychallenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieSerieDto {
    private Long id;
    private String imageMovieSerie;
    private String title;
    private String creationDate;
    private Integer rating;
    private List<CharacterDto> characters;


}
