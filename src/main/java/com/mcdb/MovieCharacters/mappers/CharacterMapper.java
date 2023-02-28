package com.mcdb.MovieCharacters.mappers;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.services.CharacterServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private CharacterServiceImpl characterService;

    //character --> characterDto

    @Mapping(target = "inMovies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDto characterToCharacterDto(Character character);
    public abstract Collection<CharacterDto> characterToCharacterDto(Collection<Character> character);

    //characterDto --> character
    public abstract Character characterDtoToCharacter(CharacterDto characterDto);


    @Named("moviesToIds")
    Set<Integer> mapSubjectsToIds(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(m -> m.getId()).collect(Collectors.toSet());
    }

}
