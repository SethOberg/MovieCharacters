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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 150, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String genre;
    @Column(length = 4, nullable = false)
    private String year;
    @Column(length = 50, nullable = false)
    private String director;
    @Column(length = 150)
    private String posterUrl;
    @Column(length = 30)
    private String trailerUrl;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private Set<Character> characters = new HashSet<>();

    public Movie(String title, String genre, String year, String director, String posterUrl, String trailerUrl) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
    }

    public void addCharacterToMovie(Character character) {
        characters.add(character);
    }
}
