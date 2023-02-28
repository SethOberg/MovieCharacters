package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.CharacterMapper;
import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private CharacterServiceImpl characterService;
    private MovieServiceImpl movieService;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterController(CharacterServiceImpl characterService, MovieServiceImpl movieService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
        this.movieService = movieService;
    }

    @PostMapping
    public void addCharacter(@RequestBody Character character) {
        characterService.add(character);
    }


    @GetMapping
    public List<CharacterDto> getAllCharacters() {
        List<CharacterDto> dtos = characterService.findAll().stream()
                .map((character -> characterMapper.characterToCharacterDto(character)))
                .collect(Collectors.toList());

        return dtos;
    }


    @GetMapping("{id}")
    public ResponseEntity getCharacter(@PathVariable Integer id) {
        CharacterDto dto = characterMapper.characterToCharacterDto(characterService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public void deleteCharacter(@PathVariable Integer id) {
        characterService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateCharacter(@PathVariable Integer id,  @RequestBody Character character) {
        characterService.update(character);
    }

    @PutMapping("{characterId}/{movieId}")
    public void addMovieToCharacter(@PathVariable Integer characterId,
                                    @PathVariable Integer movieId) {
        Character character = characterService.findById(characterId);
        Movie movie = movieService.findById(movieId);
        character.addMovieToCharacter(movie);
        movie.addCharacterToMovie(character);
        characterService.update(character);
        movieService.update(movie);
    }

}
