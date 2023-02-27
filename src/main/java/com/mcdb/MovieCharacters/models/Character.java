package com.mcdb.MovieCharacters.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
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
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;

//    public Character(int i, String name, String someAlias, String m, String s) {
//    }
}
