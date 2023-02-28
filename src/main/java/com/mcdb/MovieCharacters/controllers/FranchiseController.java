package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.FranchiseMapper;
import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.FranchiseDto;
import com.mcdb.MovieCharacters.services.FranchiseServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private FranchiseServiceImpl franchiseService;

    private MovieServiceImpl movieService;
    private final FranchiseMapper franchiseMapper;

    @Autowired
    public FranchiseController(FranchiseServiceImpl franchiseService, FranchiseMapper franchiseMapper, MovieServiceImpl movieService ) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieService = movieService;
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

    @PutMapping("{franchiseId}/{movieId}")
    public void addMovieToFranchise(@PathVariable Integer franchiseId, @PathVariable Integer movieId) {
        Franchise franchise = franchiseService.findById(franchiseId);
        Movie movie = movieService.findById(movieId);

        franchise.addMovie(movie);
        franchise.addMovie(movie);
        franchiseService.update(franchise);
        movieService.update(movie);
    }

}

