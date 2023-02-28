package com.mcdb.MovieCharacters.mappers;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.dtos.CharacterGetDTO;

public interface CharacterMapper {
    CharacterGetDTO toCharacterDto(Character character);
    Character toCharacter(CharacterGetDTO dto);
}
