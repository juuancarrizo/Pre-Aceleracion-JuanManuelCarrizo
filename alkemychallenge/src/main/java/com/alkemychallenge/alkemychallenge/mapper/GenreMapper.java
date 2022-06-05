package com.alkemychallenge.alkemychallenge.mapper;

import com.alkemychallenge.alkemychallenge.dto.GenreDto;
import com.alkemychallenge.alkemychallenge.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
    public GenreEntity genreDto2Entity(GenreDto dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setNameGenre(dto.getNameGenre());
        genreEntity.setImageGenre(dto.getImageGenre());
        return genreEntity;
    }
    public GenreDto genreEntity2Dto(GenreEntity entity){
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setNameGenre(entity.getNameGenre());
        dto.setImageGenre(entity.getImageGenre());
        return dto;
    }
    public List<GenreDto> genreEntityList2DtoList(List<GenreEntity> entities){
        List<GenreDto> dtos = new ArrayList<>();
        for(GenreEntity entity:entities){
            dtos.add(this.genreEntity2Dto(entity));
        }
        return dtos;
    }
}
