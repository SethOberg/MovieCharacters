package com.mcdb.MovieCharacters.services;

import com.mcdb.MovieCharacters.models.MovieCharacter;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public MovieCharacter findById(Integer integer) {
        return null;
    }

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return null;
    }

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
