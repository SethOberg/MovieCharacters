package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.FranchiseMapper;
import com.mcdb.MovieCharacters.mappers.MovieMapper;
import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.MovieDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.FranchiseServiceImpl;
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
        private FranchiseServiceImpl franchiseService;
        private final MovieMapper movieMapper;



        @Autowired
        public MovieController(MovieServiceImpl movieService, CharacterServiceImpl characterService, MovieMapper movieMapper, FranchiseServiceImpl franchiseService) {
            this.movieService = movieService;
            this.characterService = characterService;
            this.movieMapper = movieMapper;
            this.franchiseService = franchiseService;
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

    @PutMapping("{movieId}/{franchiseId}")
    public void addFranchiseToMovie(@PathVariable Integer movieId,
                                    @PathVariable Integer franchiseId) {
        Movie movie = movieService.findById(movieId);
        Franchise franchise = franchiseService.findById(franchiseId);
        movie.setFranchise(franchise);
        franchise.addMovieToFranchise(movie);
        franchiseService.update(franchise);
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

    @PutMapping("{movieId}/add")
    public void addCharactersToMovie(@PathVariable Integer movieId,
                                     @RequestBody List<Integer> characterIds) {
        Movie movie = movieService.findById(movieId);
        List<Character> characters = characterIds.stream()
                .map(characterService::findById)
                .collect(Collectors.toList());

        for (Character character1 : characters) {
            movie.addCharacterToMovie(character1);
            character1.addMovieToCharacter(movie);
            characterService.update(character1);

        }
        movieService.update(movie);
    }

    @PutMapping("{movieId}/{oldCharacterId}/change")
    public void changeMovieInFranchise(@PathVariable Integer movieId, @PathVariable Integer oldCharacterId,
                                       @RequestBody Integer newCharacterId) {
        Movie movie = movieService.findById(movieId);
        Character oldCharacter = characterService.findById(oldCharacterId);
        Character newCharacter = characterService.findById(newCharacterId);
        movie.deleteCharacterFromMovie(oldCharacter);
        movie.addCharacterToMovie(newCharacter);
        oldCharacter.deleteMovieFromCharacter(movie);

        movieService.update(movie);
        characterService.update(oldCharacter);
        characterService.update(newCharacter);
    }


    }




