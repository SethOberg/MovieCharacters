package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

        private MovieServiceImpl movieService;

        @Autowired
        public MovieController(MovieServiceImpl movieService) {
            this.movieService = movieService;
        }

        @PostMapping
        public void addMovie() {

        }


        @GetMapping
        public Collection<Movie> getAllMovies() {
            return movieService.findAll();
        }

        @GetMapping("{id}")
        public Movie getMovie(@PathVariable String id) {
            return null;
        }



    }



