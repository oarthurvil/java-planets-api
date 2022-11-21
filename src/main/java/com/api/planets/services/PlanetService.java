package com.api.planets.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.planets.models.Planet;
import com.api.planets.repositories.PlanetRepository;

@Service
public class PlanetService {
    
    @Autowired
    PlanetRepository planetRepository;

    @Transactional
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    public boolean existsByName(String planetName) {
        return planetRepository.existsByName(planetName);
    }

    public Page<Planet> findAll(Pageable pageable) {
        return planetRepository.findAll(pageable);
    }

    public Optional<Planet> findById(UUID id) {
        return planetRepository.findById(id);
    }

    @Transactional
    public void delete(Planet planet) {
        planetRepository.delete(planet);
    }
}
