package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.mappers.CharacterMapper;
import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    private CharacterServiceImpl characterService;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterController(CharacterServiceImpl characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @PostMapping
    public void addCharacter() {

    }


    @GetMapping
    public Collection<Character> getAllCharacters() {
        return characterService.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity getCharacter(@PathVariable Integer id) {
        CharacterDto dto = characterMapper.characterToCharacterDto(characterService.findById(id));
        return ResponseEntity.ok(dto);
    }



}
