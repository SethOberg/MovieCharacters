package com.mcdb.MovieCharacters.mappers;

import com.mcdb.MovieCharacters.models.Character;
import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.models.Movie;
import com.mcdb.MovieCharacters.models.dtos.CharacterDto;
import com.mcdb.MovieCharacters.models.dtos.FranchiseDto;
import com.mcdb.MovieCharacters.models.dtos.FranchiseGetMoviesDto;
import com.mcdb.MovieCharacters.services.FranchiseServiceImpl;
import com.mcdb.MovieCharacters.services.MovieServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {
    @Autowired
    private MovieServiceImpl movieService;
    @Autowired
    private FranchiseServiceImpl franchiseService;

    //franchise --> franchiseDto
    @Mapping(target = "moviesInFranchise", source = "movies", qualifiedByName = "moviesToIds")
    public abstract FranchiseDto franchiseToFranciseDto(Franchise franchise);

    @Mapping(target = "moviesInFranchise", source = "movies", qualifiedByName = "movies")
    public abstract FranchiseGetMoviesDto franchiseToFranchiseGetMoviesDto(Franchise franchise);

    public abstract Collection<FranchiseDto> franchiseToFranciseDto(Collection<Franchise> franchises);

    //franchiseDto --> franchise
    public abstract Franchise franchiseDtoToFranchise(FranchiseDto franchiseDto);


    @Named("moviesToIds")
    Set<Integer> mapMoviesToIds(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(m -> m.getId()).collect(Collectors.toSet());
    }

    @Named("movies")
    Set<Movie> mapMovies(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream().collect(Collectors.toSet());
    }



}
