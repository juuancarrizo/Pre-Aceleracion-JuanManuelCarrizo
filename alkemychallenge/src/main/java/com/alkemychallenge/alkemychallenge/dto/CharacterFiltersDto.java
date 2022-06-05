package com.alkemychallenge.alkemychallenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFiltersDto {
    private String nameCharacter;
    private Integer age;
    private Set<Long> movieSeries;
    private String order;

    public CharacterFiltersDto(String nameCharacter, Integer age, Set<Long> movieSeries, String order){
        this.nameCharacter = nameCharacter;
        this.age = age;
        this.movieSeries = movieSeries;
        this.order = order;
    }
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC")==0;
    }
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC")==0;
    }
}
