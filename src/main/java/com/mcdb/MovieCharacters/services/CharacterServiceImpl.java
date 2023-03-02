package com.mcdb.MovieCharacters.services;

import com.mcdb.MovieCharacters.exceptions.CharacterNotFoundException;
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
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
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
        findById(entity.getId());
        return characterRepository.save(entity);
    }
    @Override
    public void deleteById(Integer id) {
        findById(id);
        characterRepository.deleteById(id);}
}
