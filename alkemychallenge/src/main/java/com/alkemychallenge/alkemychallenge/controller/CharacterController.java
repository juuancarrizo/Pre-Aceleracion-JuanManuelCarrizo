package com.alkemychallenge.alkemychallenge.controller;

import com.alkemychallenge.alkemychallenge.dto.CharacterBasicDto;
import com.alkemychallenge.alkemychallenge.dto.CharacterDto;
import com.alkemychallenge.alkemychallenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import java.util.List;
import java.util.Set;
@RestController
@RequestMapping("Characters")
public class CharacterController {

    private CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/(all)")
    public ResponseEntity<List<CharacterBasicDto>> getAll(){
        List<CharacterBasicDto> characters = this.characterService.getAll();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getDetailsById(@PathVariable Long id){
        CharacterDto character = this.characterService.getDetailsById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getDetailsByFilters(
            @RequestParam(required = false) String nameCharacter,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movieSeries,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<CharacterDto> characters = this.characterService.getByFilters(nameCharacter,age,movieSeries,order);
        return ResponseEntity.ok(characters);
    }
    @PostMapping
    public ResponseEntity<CharacterDto> save(@RequestBody CharacterDto character){
        CharacterDto result= this.characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDto> update(@PathVariable Long id, @RequestBody CharacterDto character){
        CharacterDto result = this.characterService.update(id,character);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

