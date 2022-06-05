package com.alkemychallenge.alkemychallenge.service;

import com.alkemychallenge.alkemychallenge.dto.GenreDto;

import java.util.List;

public interface GenreService {
    GenreDto save(GenreDto dto);
    List<GenreDto> getAllGenres();


}
