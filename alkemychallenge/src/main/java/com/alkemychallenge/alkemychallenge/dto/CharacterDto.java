package com.alkemychallenge.alkemychallenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDto {
    private Long id;
    private String imageCharacter;
    private String nameCharacter;
    private Integer age;
    private Float weight;
    private String history;
    private List<MovieSerieDto> movieSeries;
}
