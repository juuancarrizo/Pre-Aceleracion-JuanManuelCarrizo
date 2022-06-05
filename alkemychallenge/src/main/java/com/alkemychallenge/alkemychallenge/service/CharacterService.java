package com.alkemychallenge.alkemychallenge.service;

import com.alkemychallenge.alkemychallenge.dto.CharacterBasicDto;
import com.alkemychallenge.alkemychallenge.dto.CharacterDto;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDto getDetailsById(Long id);

    List<CharacterBasicDto> getAll();

    List<CharacterDto> getByFilters(String nameCharacter, Integer age, Set<Long> movieSeries, String order);

    CharacterDto save(CharacterDto characterDto);

    CharacterDto update(Long id, CharacterDto character);

    void delete(Long id);

}
