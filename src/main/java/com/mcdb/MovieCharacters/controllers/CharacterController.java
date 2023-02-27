package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private CharacterServiceImpl characterService;

    @Autowired
    public CharacterController(CharacterServiceImpl characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public void addCharacter() {

    }


    @GetMapping
    public Collection<Character> getAllCharacters() {
        return characterService.findAll();
    }

    @GetMapping("{id}")
    public Character getCharacter(@PathVariable Integer id) {
        return characterService.findById(id);
    }



}
