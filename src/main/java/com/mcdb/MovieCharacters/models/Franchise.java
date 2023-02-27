package com.mcdb.MovieCharacters.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 500)
    private String description;
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies = new HashSet<>();

    public Franchise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

}
