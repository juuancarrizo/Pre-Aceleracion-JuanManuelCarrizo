package com.alkemychallenge.alkemychallenge.service.implementacion;

import com.alkemychallenge.alkemychallenge.dto.CharacterBasicDto;
import com.alkemychallenge.alkemychallenge.dto.CharacterDto;
import com.alkemychallenge.alkemychallenge.dto.CharacterFiltersDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import com.alkemychallenge.alkemychallenge.entity.CharacterEntity;
import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import com.alkemychallenge.alkemychallenge.exception.ParamNotFound;
import com.alkemychallenge.alkemychallenge.mapper.CharacterMapper;
import com.alkemychallenge.alkemychallenge.repository.CharacterRepository;
import com.alkemychallenge.alkemychallenge.repository.MovieSerieRepository;
import com.alkemychallenge.alkemychallenge.repository.specifications.CharacterSpecification;
import com.alkemychallenge.alkemychallenge.service.CharacterService;
import com.alkemychallenge.alkemychallenge.service.MovieSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class CharacterServiceImpl implements CharacterService {

    //Repo
    private CharacterRepository characterRepository;
    private CharacterSpecification characterSpecification;
    private MovieSerieRepository movieSerieRepository;
    //Mapper
    private CharacterMapper characterMapper;
    //Service
    private MovieSerieService movieSerieService;

    @Autowired
    public CharacterServiceImpl(
            CharacterRepository characterRepository,
            CharacterSpecification characterSpecification,
            CharacterMapper characterMapper,
            MovieSerieService movieSerieService){
        this.characterRepository=characterRepository;
        this.characterSpecification=characterSpecification;
        this.characterMapper = characterMapper;
        this.movieSerieService = movieSerieService;
    }


    public CharacterDto getDetailsById(Long id){
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id Caracter no valido");
        }
        CharacterDto characterDto = this.characterMapper.characterEntity2DTO(entity.get(),true);
        return characterDto;
    }

    public List<CharacterBasicDto> getAll(){
        List<CharacterEntity> entities = this.characterRepository.findAll();
        List<CharacterBasicDto> characterBasicDtos = this.characterMapper.characterEntitySet2BasicDTOList(entities);
        return characterBasicDtos;
    }
    public List<CharacterDto> getByFilters(String nameCharacter, Integer age, Set<Long> movieSeries, String order){
        CharacterFiltersDto filtersDto = new CharacterFiltersDto(nameCharacter, age, movieSeries, order);
        List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDto));
        List<CharacterDto> dtos = this.characterMapper.characterEntitySet2DTOList(entities,true);
        return dtos;
    }
    public CharacterDto save(CharacterDto characterDto){
        CharacterEntity entity = this.characterMapper.characterDTO2Entity((characterDto));
        CharacterEntity entitySaved = this.characterRepository.save(entity);
        CharacterDto result= this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }


    public CharacterDto update(Long id, CharacterDto characterDto) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("id Character no valido");
        }
        this.characterMapper.characterEntityRefreshValues(entity.get(), characterDto);
        CharacterEntity entitySaved = this.characterRepository.save(entity.get());
        CharacterDto result= this.characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }


    public void delete(Long id){
        this.characterRepository.deleteById(id);
    }


}
