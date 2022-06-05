package com.alkemychallenge.alkemychallenge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSerieFiltersDto {
    private String title;
    private String order;

    public MovieSerieFiltersDto(String title, String order){
        this.title=title;
        this.order=order;
    }
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC")==0;
    }

}
