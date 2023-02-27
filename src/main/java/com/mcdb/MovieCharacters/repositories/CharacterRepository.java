package com.mcdb.MovieCharacters.repositories;

import com.mcdb.MovieCharacters.models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<MovieCharacter, Integer> {
}
