package com.mcdb.MovieCharacters.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class FranchiseDto {
    private int id;
    private String title;
    private String description;
    private Set<Integer> moviesInFranchise;

}
