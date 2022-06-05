package com.alkemychallenge.alkemychallenge.controller;

import com.alkemychallenge.alkemychallenge.dto.GenreDto;
import com.alkemychallenge.alkemychallenge.service.GenreService;
import com.alkemychallenge.alkemychallenge.service.implementacion.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll(){
        List<GenreDto> genres = genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }
    @PostMapping
    public ResponseEntity<GenreDto> save(@RequestBody GenreDto genre){
        GenreDto genreSave = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSave);

    }

}
