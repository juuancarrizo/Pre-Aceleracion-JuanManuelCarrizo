package com.alkemychallenge.alkemychallenge.service;

import com.alkemychallenge.alkemychallenge.dto.MovieSerieBasicDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;

import java.util.List;

public interface MovieSerieService {

    MovieSerieDto getDetailsById(Long id);

    List<MovieSerieBasicDto> getAll();

    List<MovieSerieDto> getByFilters(String title, String order);

    MovieSerieDto save(MovieSerieDto movieSerie);

    MovieSerieDto update(Long id, MovieSerieDto movieSerie);

    void delete(Long id);

    void addCharacter(Long id, Long idCharacter);

    void removeCharacter(Long id, Long idCharacter);
}
