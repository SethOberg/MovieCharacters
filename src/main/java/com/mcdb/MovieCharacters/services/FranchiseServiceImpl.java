package com.mcdb.MovieCharacters.services;

import com.mcdb.MovieCharacters.models.Franchise;
import com.mcdb.MovieCharacters.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise findById(Integer integer) {
        Optional<Franchise> franchise = franchiseRepository.findById(integer);
        return franchise.get();
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }


    @Override
    public void deleteById(Integer integer) {
        franchiseRepository.deleteById(integer);
    }
}
