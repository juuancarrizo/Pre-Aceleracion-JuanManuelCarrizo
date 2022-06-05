package com.alkemychallenge.alkemychallenge.service.implementacion;

import com.alkemychallenge.alkemychallenge.dto.MovieSerieBasicDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieFiltersDto;
import com.alkemychallenge.alkemychallenge.entity.CharacterEntity;
import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import com.alkemychallenge.alkemychallenge.exception.ParamNotFound;
import com.alkemychallenge.alkemychallenge.mapper.MovieSerieMapper;
import com.alkemychallenge.alkemychallenge.repository.CharacterRepository;
import com.alkemychallenge.alkemychallenge.repository.MovieSerieRepository;
import com.alkemychallenge.alkemychallenge.repository.specifications.MovieSerieSpecification;
import com.alkemychallenge.alkemychallenge.service.MovieSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieSerieServiceImpl implements MovieSerieService {

    //Repo
    private MovieSerieRepository movieSerieRepository;
    private CharacterRepository characterRepository;
    private MovieSerieSpecification movieSerieSpecification;

    //Mapper
    private MovieSerieMapper movieSerieMapper;

    //Service
    private MovieSerieService movieSerieService;

    @Autowired
    public MovieSerieServiceImpl(
            MovieSerieRepository movieSerieRepository,
            MovieSerieSpecification movieSerieSpecification,
            MovieSerieMapper movieSerieMapper){
        this.movieSerieRepository=movieSerieRepository;
        this.movieSerieSpecification = movieSerieSpecification;
        this.movieSerieMapper=movieSerieMapper;
    }

    public MovieSerieDto getDetailsById(Long id){
        Optional<MovieSerieEntity> entity= this.movieSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id Movie Serie no valido");
        }
        MovieSerieDto movieSerieDto= this.movieSerieMapper.movieSerieEntity2DTO(entity.get(),true);
        return movieSerieDto;
    }

    public List<MovieSerieBasicDto> getAll(){
        List<MovieSerieEntity> entities = this.movieSerieRepository.findAll();
        List<MovieSerieBasicDto> movieSerieDtos= this.movieSerieMapper.movieSerieEntityList2DTOList(entities);
        return movieSerieDtos;
    }
    public List<MovieSerieDto> getByFilters(String title, String order){
        MovieSerieFiltersDto filtersDto = new MovieSerieFiltersDto(title,order);
        List<MovieSerieEntity> entities = this.movieSerieRepository.findAll((Sort)this.movieSerieSpecification.getByFilters(filtersDto));
        List<MovieSerieDto> dtos= this.movieSerieMapper.movieSerieEntityList2DTOList(entities,true);
        return dtos;
    }

    @Override
    public MovieSerieDto save(MovieSerieDto movieSerie) {
        return null;
    }

    @Override
    public MovieSerieDto update(Long id, MovieSerieDto movieSerie) {
        return null;
    }

    @Override
    public void delete(Long id) {
        this.movieSerieRepository.deleteById(id);
    }

    @Override
    public void addCharacter(Long id, Long idCharacter) {
        MovieSerieEntity entity = this.movieSerieRepository.findById(id).orElse(null);
        if(entity==null){
            throw new ParamNotFound("Error id film");
        }
        CharacterEntity characterEntity = this.characterRepository.findById(idCharacter).orElse(null);
        if(characterEntity==null){
            throw new ParamNotFound("Error Character");
        }
        entity.getCharacters().add(characterEntity);
        this.movieSerieRepository.save(entity);

    }

    @Override
    public void removeCharacter(Long id, Long idCharacter) {
        MovieSerieEntity entity = this.movieSerieRepository.findById(id).orElse(null);
        if(entity==null){
            throw new ParamNotFound("Error id film");
        }
        CharacterEntity characterEntity = this.characterRepository.findById(idCharacter).orElse(null);
        if(characterEntity==null){
            throw new ParamNotFound("Error Character");
        }
        entity.getCharacters().remove(characterEntity);
        this.movieSerieRepository.save(entity);
    }

}
