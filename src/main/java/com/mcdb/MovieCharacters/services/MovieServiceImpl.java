package com.mcdb.MovieCharacters.services;


import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
    public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public Movie findById(Integer integer) {
        Optional<Movie> movie = movieRepository.findById(integer);
            return movie.get() ;
        }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }
    @Override
    public Movie add(Movie entity) { return movieRepository.save(entity); }
    @Override
    public Movie update(Movie entity) { return movieRepository.save(entity); }
    @Override
    public void deleteById(Integer integer) { movieRepository.deleteById(integer); }

    //TODO Alla metoder står ej inskrivna här
}

