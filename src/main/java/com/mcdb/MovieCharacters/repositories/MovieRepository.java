package com.mcdb.MovieCharacters.repositories;

import com.mcdb.MovieCharacters.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
