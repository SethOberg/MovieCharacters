package com.mcdb.MovieCharacters.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{
    public FranchiseNotFoundException(int id) {
        super("Franchise does not exist with ID: " + id);
    }
}
