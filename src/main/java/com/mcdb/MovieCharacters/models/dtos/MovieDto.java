package com.mcdb.MovieCharacters.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcdb.MovieCharacters.models.Franchise;
import lombok.Data;

import java.util.Set;

@Data
public class MovieDto {
    private int id;
    private String title;
    private String genre;
    private String year;
    private String director;
    private String posterUrl;
    private String trailerUrl;
    private Integer franchiseId;
    private Set<Integer> charactersIn;

}
