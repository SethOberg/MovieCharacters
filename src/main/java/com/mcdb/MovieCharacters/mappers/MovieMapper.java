package com.mcdb.MovieCharacters.mappers;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.MovieDto;
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
public abstract class MovieMapper {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private CharacterServiceImpl characterService;

    //movie --> movieDto

    @Mapping(target = "charactersIn", source = "characters", qualifiedByName = "charactersToIds")
    @Mapping(target = "franchiseId", source = "franchise.id")
    public abstract MovieDto moveToMovieDto(Movie movie);
    public abstract Collection<MovieDto> movieToMovieDto(Collection<Movie> movie);

    public abstract Movie movieDtoToMovie(MovieDto movieDto);

    @Named("charactersToIds")
    Set<Integer>mapCharactersToIds(Set<Character> source) {
        if (source == null)
            return null;
        return source.stream()
                .map(m -> m.getId()).collect(Collectors.toSet());

    }


}
