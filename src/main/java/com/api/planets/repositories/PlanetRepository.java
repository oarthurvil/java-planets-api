package com.api.planets.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.planets.models.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, UUID>{
    
    boolean existsByName(String planetName);
}
