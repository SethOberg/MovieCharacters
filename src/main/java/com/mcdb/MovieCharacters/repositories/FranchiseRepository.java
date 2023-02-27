package com.mcdb.MovieCharacters.repositories;

import com.mcdb.MovieCharacters.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
