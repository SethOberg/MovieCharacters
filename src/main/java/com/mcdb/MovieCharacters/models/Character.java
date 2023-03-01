package com.mcdb.MovieCharacters.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 50, nullable = false)
    private String alias;
    @Column(length = 50)
    private String gender;
    @Column(length = 150)
    private String pictureUrl;


    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies = new HashSet<>();


    public Character(String fullName, String alias, String gender, String pictureUrl) {
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.pictureUrl = pictureUrl;
    }

    public void addMovieToCharacter(Movie movie) {
        movies.add(movie);
    }

    public void deleteMovieFromCharacter(Movie movie) {
        movies.remove(movie);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
