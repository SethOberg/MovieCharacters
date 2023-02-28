package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.MovieMapper;
import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.MovieDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
        private MovieServiceImpl movieService;
        private CharacterServiceImpl characterService;
        private final MovieMapper movieMapper;



        @Autowired
        public MovieController(MovieServiceImpl movieService, CharacterServiceImpl characterService, MovieMapper movieMapper) {
            this.movieService = movieService;
            this.characterService = characterService;
            this.movieMapper = movieMapper;
        }

        @PostMapping
        public void addMovie(@RequestBody Movie movie) { movieService.add(movie);
        }

        @GetMapping
        public List<MovieDto> getAllMovies() {
            List<MovieDto> dtos = movieService.findAll().stream()
                    .map((movie -> movieMapper.moveToMovieDto(movie)))
                    .collect(Collectors.toList());

            return dtos;
        }

        @GetMapping("{id}")
        public ResponseEntity getMovie(@PathVariable Integer id) {
            MovieDto dto = movieMapper.moveToMovieDto(movieService.findById(id));
            return ResponseEntity.ok(dto);
        }

    @DeleteMapping("{id}")
    public void deleteCharacter(@PathVariable Integer id) {
        movieService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateMovie(@PathVariable Integer id,  @RequestBody Movie movie) {
        movieService.update(movie);
    }

    @PutMapping("{movieId}/{characterId}")
    public void addCharacterToMovie(@PathVariable Integer movieId,
                                    @PathVariable Integer characterId) {
        Movie movie = movieService.findById(movieId);
        Character character = characterService.findById(characterId);
        movie.addCharacterToMovie(character);
        character.addMovieToCharacter(movie);
        movieService.update(movie);
        characterService.update(character);
    }

    }



