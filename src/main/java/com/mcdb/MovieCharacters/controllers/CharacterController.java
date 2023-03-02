package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.CharacterMapper;
import com.mcdb.MovieCharacters.mappers.MovieMapper;
import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.models.dtos.MovieDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import com.mcdb.MovieCharacters.util.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private CharacterServiceImpl characterService;
    private MovieServiceImpl movieService;
    private final CharacterMapper characterMapper;
    private final MovieMapper movieMapper;

    @Autowired
    public CharacterController(CharacterServiceImpl characterService, MovieServiceImpl movieService,
                               CharacterMapper characterMapper, MovieMapper movieMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
        this.movieService = movieService;
        this.movieMapper = movieMapper;
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


    @Operation(summary = "Get character by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDto.class)) }
            ),
            @ApiResponse(responseCode = "404",
                    description = "Character does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })

    })
    @GetMapping("{id}")
    public ResponseEntity getCharacter(@PathVariable Integer id) {
        CharacterDto dto = characterMapper.characterToCharacterDto(characterService.findById(id));

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public void deleteCharacter(@PathVariable Integer id) {
        List<Movie> allMovies = (List<Movie>) movieService.findAll();

        for(Movie movie : allMovies) {
            Set<Character> characters = movie.getCharacters().stream()
                    .filter(character -> character.getId() != id)
                    .collect(Collectors.toSet());
            movie.setCharacters(characters);
            movieService.update(movie);
        }

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

    @GetMapping("{id}/movies")
    public List<MovieDto> getMoviesCharacterIsIn(@PathVariable Integer id) {
        List<MovieDto> movieDtos = characterService.findById(id).getMovies()
                .stream()
                .map((movie -> movieMapper.moveToMovieDto(movie)))
                .collect(Collectors.toList());

        return movieDtos;
    }

}
