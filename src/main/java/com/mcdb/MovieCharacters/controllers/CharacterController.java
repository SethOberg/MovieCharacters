package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.models.MovieCharacter;
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
    public Collection<MovieCharacter> getAllCharacters() {
        return characterService.findAll();
    }

    @GetMapping("{id}")
    public MovieCharacter getCharacter(@PathVariable String id) {
        return null;
    }



}
