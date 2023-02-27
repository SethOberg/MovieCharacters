package com.mcdb.MovieCharacters.services;


import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
    public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public Movie findById(Integer integer) {
            return null;
        }
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) { return null; }
    @Override
    public Movie update(Movie entity) { return null; }
    @Override
    public void deleteById(Integer integer) { }
}

