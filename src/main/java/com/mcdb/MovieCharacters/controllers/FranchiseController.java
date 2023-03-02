package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.CharacterMapper;
import com.mcdb.MovieCharacters.mappers.FranchiseMapper;
import com.mcdb.MovieCharacters.mappers.MovieMapper;
import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.models.dtos.FranchiseDto;
import com.mcdb.MovieCharacters.models.dtos.FranchiseGetMoviesDto;
import com.mcdb.MovieCharacters.models.dtos.MovieDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.FranchiseServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private FranchiseServiceImpl franchiseService;

    private MovieServiceImpl movieService;

    private CharacterServiceImpl characterService;

    private final FranchiseMapper franchiseMapper;

    private final MovieMapper movieMapper;

    private final CharacterMapper characterMapper;

    @Autowired
    public FranchiseController(FranchiseServiceImpl franchiseService, FranchiseMapper franchiseMapper,
                               MovieServiceImpl movieService, MovieMapper movieMapper,
                               CharacterServiceImpl characterService,
                               CharacterMapper characterMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @PostMapping
    public void addFranchise(@RequestBody Franchise franchise) {
        franchiseService.add(franchise);
    }

    @GetMapping
    public List<FranchiseDto> getAllFranchises() {
        List<FranchiseDto> franchiseDtos = franchiseService.findAll().stream()
                .map((franchise -> franchiseMapper.franchiseToFranciseDto(franchise)))
                .collect(Collectors.toList());
        return franchiseDtos;
    }

    @GetMapping("{id}")
    public FranchiseDto getFranchise(@PathVariable Integer id) {
        FranchiseDto franchiseDto = franchiseMapper.franchiseToFranciseDto(franchiseService.findById(id));
        return franchiseDto;
    }

    @DeleteMapping("{id}")
    public void deleteFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseService.findById(id);

        franchise.getMovies().stream()
                .forEach((movie -> movie.setFranchise(null)));

        franchiseService.deleteById(id);
    }

    @PutMapping("{franchiseId}/{movieId}")
    public void addMoviesToFranchise(@PathVariable Integer franchiseId, @PathVariable Integer movieId) {
        Franchise franchise = franchiseService.findById(franchiseId);
        Movie movie = movieService.findById(movieId);

        franchise.addMovieToFranchise(movie);
        franchise.addMovieToFranchise(movie);
        franchiseService.update(franchise);
        movieService.update(movie);
    }

    @PutMapping("{franchiseId}/add")
    public void addMoviesToFranchise(@PathVariable Integer franchiseId, @RequestBody List<Integer> movieIds) {
        Franchise franchise = franchiseService.findById(franchiseId);
        List<Movie> movies = movieIds.stream()
                .map(movieService::findById)
                .collect(Collectors.toList());

        for (Movie movie1 : movies) {
            franchise.addMovieToFranchise(movie1);
            movie1.setFranchise(franchise);
            movieService.update(movie1);
        }
        franchiseService.update(franchise);

}

    @PutMapping("{franchiseId}/{oldMovieId}/change")
    public void changeMovieInFranchise(@PathVariable Integer franchiseId, @PathVariable Integer oldMovieId,
                                       @RequestBody Integer newMovieId) {
        Franchise franchise = franchiseService.findById(franchiseId);
        Movie oldMovie = movieService.findById(oldMovieId);
        Movie newMovie = movieService.findById(newMovieId);
        franchise.deleteMovieFromFranchise(oldMovie);
        franchise.addMovieToFranchise(newMovie);
        newMovie.setFranchise(franchise);
        oldMovie.setFranchise(null);

        franchiseService.update(franchise);
        movieService.update(oldMovie);
        movieService.update(newMovie);
    }


    @GetMapping("{id}/movies")
    public List<MovieDto> getMoviesInFranchise(@PathVariable Integer id) {
        List<MovieDto> movieDtos = franchiseService.findById(id).getMovies().stream().map((movie) -> movieMapper.moveToMovieDto(movie)).collect(Collectors.toList());

        return movieDtos;
    }

    @GetMapping("{id}/movies/characters")
    public List<CharacterDto> getCharactersInFranchise(@PathVariable Integer id) {
        List<Movie> movies = franchiseService.findById(id).getMovies().stream().collect(Collectors.toList());
        List<CharacterDto> characterDtos =
                movies.stream()
                        .flatMap((characters) -> characters.getCharacters().stream())
                        .map(character -> characterMapper.characterToCharacterDto(character))
                        .distinct()
                        .collect(Collectors.toList());

        return characterDtos;
    }


}

