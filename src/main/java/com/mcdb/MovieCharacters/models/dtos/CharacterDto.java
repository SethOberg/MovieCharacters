package com.mcdb.MovieCharacters.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcdb.MovieCharacters.models.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CharacterDto {
    private int id;
    private String fullName;
    private String alias;
    private String gender;
    private String pictureUrl;
    private Set<Integer> inMovies;
}
