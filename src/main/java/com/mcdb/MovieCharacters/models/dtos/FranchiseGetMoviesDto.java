package com.mcdb.MovieCharacters.models.dtos;

import com.mcdb.MovieCharacters.models.Movie;
import lombok.Data;

import java.util.Set;

@Data
public class FranchiseGetMoviesDto {

    private Set<Movie> moviesInFranchise;

}
