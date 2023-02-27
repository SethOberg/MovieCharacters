package com.mcdb.MovieCharacters.repositories;

import com.mcdb.MovieCharacters.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
