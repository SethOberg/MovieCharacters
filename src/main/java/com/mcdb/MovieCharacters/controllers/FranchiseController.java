package com.mcdb.MovieCharacters.controllers;

import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.services.FranchiseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    private FranchiseServiceImpl franchiseService;

    @Autowired
    public FranchiseController(FranchiseServiceImpl characterService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public void addFranchise() {
    }

    @GetMapping
    public Collection<Franchise> getAllFranchises() {
        return franchiseService.findAll();
    }

    @GetMapping("{id}")
    public Franchise getFranchise(@PathVariable String id) {
        return null;
    }

}

