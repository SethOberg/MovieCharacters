package com.mcdb.MovieCharacters.services;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Character findById(Integer id) {
        Optional<Character> character = characterRepository.findById(id);

        return character.get();
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        characterRepository.deleteById(integer);
    }
}
