package com.alkemychallenge.alkemychallenge.controller;

import com.alkemychallenge.alkemychallenge.dto.MovieSerieBasicDto;
import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import com.alkemychallenge.alkemychallenge.service.MovieSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("MovieSeries")
public class MovieSerieController {

    private MovieSerieService movieSerieService;

    @Autowired
    public MovieSerieController(MovieSerieService movieSerieService){
        this.movieSerieService=movieSerieService;
    }
    @GetMapping("/(all)")
    public ResponseEntity<List<MovieSerieBasicDto>> getAll(){
        List<MovieSerieBasicDto> movieSeries = this.movieSerieService.getAll();
        return ResponseEntity.ok(movieSeries);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieSerieDto> getDetailsById(@PathVariable Long id){
        MovieSerieDto movieSerie = this.movieSerieService.getDetailsById(id);
        return ResponseEntity.ok(movieSerie);
    }

    @GetMapping
    public ResponseEntity<List<MovieSerieDto>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "ASC") String order){
        List<MovieSerieDto> movieSeries = this.movieSerieService.getByFilters(title,order);
        return ResponseEntity.ok(movieSeries);
    }
    @PostMapping
    public ResponseEntity<MovieSerieDto> save(@RequestBody MovieSerieDto movieSerie){
        MovieSerieDto result= this.movieSerieService.save(movieSerie);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieSerieDto> update(@PathVariable Long id, @RequestBody MovieSerieDto movieSerie){
        MovieSerieDto result= this.movieSerieService.update(id,movieSerie);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.movieSerieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("{id}/character/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long id,@PathVariable Long idCharacter){
        this.movieSerieService.addCharacter(id,idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("{id}/character/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long id,@PathVariable Long idCharacter) {
        this.movieSerieService.removeCharacter(id, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
