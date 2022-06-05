package com.alkemychallenge.alkemychallenge.mapper;

import com.alkemychallenge.alkemychallenge.dto.CharacterDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieBasicDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import com.alkemychallenge.alkemychallenge.entity.CharacterEntity;
import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MovieSerieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public MovieSerieEntity movieSerieDTO2Entity(MovieSerieDto dto){
        MovieSerieEntity entity = new MovieSerieEntity();
        entity.setImageMovieSerie(dto.getImageMovieSerie());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(this.string2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        return entity;
    }
    public MovieSerieDto movieSerieEntity2DTO(MovieSerieEntity entity, boolean loadCharacters){
        MovieSerieDto dto = new MovieSerieDto();
        dto.setId(entity.getId());
        dto.setImageMovieSerie(entity.getImageMovieSerie());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        if(loadCharacters){

            List<CharacterDto> charactersDto = this.characterMapper.characterEntityList2DTO((List<CharacterEntity>) entity.getCharacters(),false);
            dto.setCharacters(charactersDto);
        }
        return dto;
    }
    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }
    public void movieSerieEntityRefreshValues(MovieSerieEntity entity, MovieSerieDto movieSerieDto){
        entity.setImageMovieSerie(movieSerieDto.getImageMovieSerie());
        entity.setTitle(movieSerieDto.getTitle());
        entity.setCreationDate(this.string2LocalDate(movieSerieDto.getCreationDate()));
        entity.setRating(movieSerieDto.getRating());
    }

    //ESTA BIEN
    public List<MovieSerieBasicDto> movieSerieEntityList2DTOList(Collection<MovieSerieEntity> entities){
        List<MovieSerieBasicDto> dtos = new ArrayList<>();
        MovieSerieBasicDto basicDto;
        for (MovieSerieEntity entity:entities){
            basicDto = new MovieSerieBasicDto();
            basicDto.setId(entity.getId());
            basicDto.setTitle(entity.getTitle());
            basicDto.setIdGenre(entity.getIdGenre());
            dtos.add(basicDto);
        }
        return dtos;
    }
    //ESTA BIEN
    public List<MovieSerieDto> movieSerieEntityList2DTOList(Collection<MovieSerieEntity> entities, boolean loadCharacters){
        List<MovieSerieDto> dtos = new ArrayList<>();
        for(MovieSerieEntity entity : entities){
            dtos.add(this.movieSerieEntity2DTO(entity,loadCharacters));
        }
        return dtos;
    }
    public List<MovieSerieEntity> movieSerieDTOList2Entity(List<MovieSerieDto> dtos){
        List<MovieSerieEntity> entities = new ArrayList<>();
        for (MovieSerieDto dto:dtos){
            entities.add(this.movieSerieDTO2Entity(dto));
        }
        return entities;
    }
}
