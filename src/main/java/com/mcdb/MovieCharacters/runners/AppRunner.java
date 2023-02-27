package com.mcdb.MovieCharacters.runners;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashSet;

public class AppRunner implements ApplicationRunner {
    private CharacterRepository characterRepository;

    @Autowired
    public AppRunner(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Character test = new Character(3, "name", "some alias", "m", "someurl/image/someid.jpg", new HashSet<>());
        characterRepository.save(test);
    }
}
