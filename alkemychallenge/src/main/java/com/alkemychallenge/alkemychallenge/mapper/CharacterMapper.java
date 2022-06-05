package com.alkemychallenge.alkemychallenge.mapper;

import com.alkemychallenge.alkemychallenge.dto.CharacterBasicDto;
import com.alkemychallenge.alkemychallenge.dto.CharacterDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import com.alkemychallenge.alkemychallenge.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieSerieMapper movieSerieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDto dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setImageCharacter(dto.getImageCharacter());
        entity.setNameCharacter(dto.getNameCharacter());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        return entity;
    }
    public CharacterDto characterEntity2DTO(CharacterEntity entity, boolean loadMovieSeries){
        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setImageCharacter(entity.getImageCharacter());
        dto.setNameCharacter(entity.getNameCharacter());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if(loadMovieSeries){
            List<MovieSerieDto> movieSerieDto = this.movieSerieMapper.movieSerieEntityList2DTOList(entity.getMovieSeries(),false);
            dto.setMovieSeries(movieSerieDto);

        }
        return dto;
    }
    public void characterEntityRefreshValues(CharacterEntity entity,CharacterDto characterDto){
        entity.setImageCharacter(characterDto.getImageCharacter());
        entity.setNameCharacter(characterDto.getNameCharacter());
        entity.setAge(characterDto.getAge());
        entity.setWeight(characterDto.getWeight());
        entity.setHistory(characterDto.getHistory());
    }

    public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDto> dtos){
        Set<CharacterEntity> entities = new HashSet<>();
        for(CharacterDto dto: dtos){
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }
    /**
     * @param entities (Set or List)
     * @param loadMovieSerie
     */

    public List<CharacterDto> characterEntitySet2DTOList(Collection<CharacterEntity> entities, boolean loadMovieSerie){
        List<CharacterDto> dtos = new ArrayList<>();
        for (CharacterEntity entity:entities){
            dtos.add(this.characterEntity2DTO(entity,loadMovieSerie));
        }
        return dtos;
    }

    public List<CharacterBasicDto> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities){
        List<CharacterBasicDto> dtos = new ArrayList<>();
        CharacterBasicDto basicDto;
        for (CharacterEntity entity:entities){
            basicDto = new CharacterBasicDto();
            basicDto.setId((entity.getId()));
            basicDto.setImageCharacter(entity.getImageCharacter());
            basicDto.setNameCharacter(entity.getNameCharacter());
            dtos.add(basicDto);
        }
        return dtos;
    }

    //controlar
    public List<CharacterDto> characterEntityList2DTO(List<CharacterEntity> entities, boolean loadMovieSerie) {
        List<CharacterDto> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(this.characterEntity2DTO(entity, loadMovieSerie));
        }
        return dtos;
    }
}
